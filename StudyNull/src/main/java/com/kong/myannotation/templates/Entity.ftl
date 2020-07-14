package ${packageName};
 import lombok.Getter;
 import lombok.Setter;

 @Getter
 @Setter
 public final class ${entityName}{
 	/**
 	 * 属性关键字
 	 */
     <#list fields as field>
     public ${field.type} ${field.name};	//${field.comment}
     </#list>
 }