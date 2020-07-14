package com.kong.myannotation;

import com.kong.myannotation.enumm.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.beans.Transient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class GenDB {
    public final static String TEMP_NAME = "Entity.ftl";
    /**
     * 获取不同类型默认长度
     * @param type
     * @return
     * @throws Exception
     */
    public static  int getDefaultLength(String type) throws Exception {
        int length = 0;
        if(type == null) {
            throw new RuntimeException("不能识别的类型:type is null");
        }
        // 根据不同类型返回不同默认长度
        if("int".equals(type)) {
            length = 11;;
        } else if("long".equals(type)) {
            length = 20;
        } else if("String".equals(type)) {
            length = 255;
        } else if("boolean".equals(type)){
            length = 4;
        }else if("tinyint".equals(type)){
            length = 4;
        }

        return length;
    }

    public String getSqlType(String type) {
        String result = null;
        if("int".equals(type)) {
            result = "int";
        } else if("long".equals(type)) {
            result = "bigint";
        } else if("String".equals(type)) {
            result = "varchar";
        } else if("boolean".equals(type)) {
            result = "tinyint";
        } else if("double".equals(type)) {
            result = "double";
        } else if("float".equals(type)) {
            result = "float";
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        //第一步 获取类上面的注解信息
        Class clazz = Person.class;
        Kong kong = (Kong)clazz.getAnnotation(Kong.class);
//        System.out.println(kong.tableName());

        //第二步 获取类中所有的字段
        ArrayList<Enum<?>> result = new ArrayList<Enum<?>>();
        Enum<?>[] enums = (Enum[]) clazz.getEnumConstants();
        for(Enum<?> en : enums) {
            // 排除有@Transient注解的字段成员
            if(en.getClass().getField(en.name()).isAnnotationPresent(Transient.class)) {
                continue;
            }
            result.add(en);
//            System.out.println(en.name());
        }

        //第三步 获取字段上的注解信息
        for (Enum<?> e : result) {
            String typeName = ((Class<?>) getFieldInfo(e, "type")).getSimpleName();
            String comment =  (String)getFieldInfo(e, "comment");
//            System.out.println(comment);
            int lengthType = getDefaultLength(typeName);
//            System.out.println(lengthType);
        }
        String sql = getSql(kong, result);

        //第四步 连接数据库 生成表
        JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSource());
//        jdbcTemplate.execute("create table temp(id int primary key,name varchar(32))");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println(sql);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        jdbcTemplate.execute(sql);


        //第五步生成 实体类
        Map<String, Object> rootMap = new HashMap<>();
        List<Map<String, Object>> fields = new ArrayList<Map<String, Object>>();
        String packageName = "com.kong.myannotation.entity";
        String entityName = clazz.getSimpleName() + "Entity";
        for (Enum<?> e : result) {
            Map<String, Object> field = new HashMap<String, Object>();
            String typeName = ((Class<?>) getFieldInfo(e, "type")).getSimpleName();
            String comment =  (String)getFieldInfo(e, "comment");
            int lengthType = getDefaultLength(typeName);
            field.put("name", e.toString());
            field.put("type", typeName);
            field.put("comment", comment);
            fields.add(field);
        }
        rootMap.put("packageName", packageName);
        rootMap.put("entityName", entityName);
        rootMap.put("fields", fields);
        rootMap.put("templateName", TEMP_NAME);
        writeFile("/src/main/java/com/kong/myannotation/entity/PersonEntity.java", TEMP_NAME, rootMap);
    }

    private static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mydemodb?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    /**
     * 根据enumm获得其Column注解的name值
     * @param enumm
     * @param name
     * @return
     * @throws Exception
     */
    public static Object getFieldInfo(Object enumm, String name) throws Exception{
        // 获取@Column注解 和 和想要的方法
        String enumName = ((Enum<?>)enumm).name(); 			// 先获取枚举自身名称如sn, level, type, sex...
        Annotation annotation = enumm.getClass().getField(enumName).getAnnotation(Conlumn.class);	// 再根据enum获取对应name字段的注解
        Method method = Conlumn.class.getMethod(name);

        Object result = method.invoke(annotation);
        return result;
    }


    private static String getSql(Kong kong, ArrayList<Enum<?>> result) throws Exception {
        //第四步 生成表 TODO 修改表
        String primaryKey = "";
        String sql = "create table " + kong.tableName() + " (";
        for (Enum<?> e : result) {
            String typeName = ((Class<?>) getFieldInfo(e, "type")).getSimpleName();
            String comment =  (String)getFieldInfo(e, "comment");
            int lengthType = getDefaultLength(typeName);
            boolean key = (Boolean)getFieldInfo(e, "index");
            if(key){
                primaryKey  = e.name();
            }
            if(typeName.equals("String")){
                typeName = "varchar";
            }else if(typeName.equals("long")){
                typeName = "bigint";
            }else if(typeName.equals("boolean")){
                typeName = "int";
            }
            sql += e.name() + " " + typeName + "(" + lengthType + ")" + " NOT NULL Comment '" + comment + "',";
        }
        sql += "Primary key (" + primaryKey + "))";
        return sql;
    }


    protected static void writeFile(String fileName, String tempName, Object rootMap) throws Exception {
        /* 1，获取模板 */
        String pageEncoding = "UTF-8";
        String userDir = System.getProperty("user.dir");
        Configuration configuration = new Configuration();
        String tempDir = userDir + "/src/main/java/com/kong/myannotation/templates";
        configuration.setDirectoryForTemplateLoading(new File(tempDir));
        configuration.setEncoding(Locale.getDefault(), pageEncoding);
        Template temp = configuration.getTemplate(tempName, pageEncoding);
        temp.setEncoding(pageEncoding);

        // 判断目标文件夹不存在，则新建文件夹
        File dir = new File(userDir);
        if(!dir.exists()) dir.mkdirs();

        /* 2，将模板和数据合并，并生成文件 */
        String fileFullName = userDir + fileName;
        System.out.println("-------开始生成" + fileFullName + "文件......------");

        File target = new File(fileFullName);
        Writer out = new OutputStreamWriter(new FileOutputStream(target), pageEncoding);
        temp.process(rootMap, out);
        out.flush();
        out.close();

        System.out.println("-------" + fileName + "文件生成完毕!-------\n");
    }

//    public void getAnnotationInfo(){
//        Class clazz = Person.class;
//        Enum<?>[] enums = (Enum[])clazz.getEnumConstants();
//        if (!clazz.isAnnotationPresent(Kong.class) || !clazz.isAnnotationPresent(Conlumn.class)) {
//            System.out.println("goodbye wolrd");
//            return;
//        }
//        Kong kong = (Kong)clazz.getAnnotation(Kong.class);
//
//    }


//    public void genDB(Connection conn) {
//        try {
//            // 获取源文件夹下的所有类
//            List<Class<?>> sources;
//            sources = GenBase.getSources(sourceDir);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 获得要生成的类
//     * @param packageName
//     * @return
//     */
//    public static List<Class<?>> getSources(String packageName) {
//        Set<Class<?>> sources = null;
//
//        if(StringUtils.isNotEmpty(GenBase.pluginDesFileName)) {
//            sources = GenBase.getPluginGenDesFile(packageName);
//        } else {
//            // 获取待删除文件夹下的所有类
//            sources = PackageClass.find(packageName);
//        }
//
//        List<Class<?>>  result = new ArrayList<>();
//        result.addAll(sources);
//
//        Collections.sort(result, (a, b) -> a.getName().compareTo(b.getName()));
//
//        return result;
//    }
}
