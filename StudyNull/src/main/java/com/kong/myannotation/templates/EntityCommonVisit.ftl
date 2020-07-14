<#-- 自定义宏，一些属性用来同步数据库用的，避免多处重写就写到这儿了 -->
<#-- 这是get和set方法的宏定义 -->
<#macro getAndSetField fields>
<#list fields as field>
<#-- **************不要持久化到数据库中的****************** -->
	/**
	 * ${field.comment}  *此为非持久化属性，请注意*
	 */
	<#-- **************除了布尔类型之外的属性调用函数*********** -->
	<#if field.type!="boolean">
	public ${field.type} get${field.name?cap_first}() {
		return getValueTrans("${field.name}");
	}

	public void set${field.name?cap_first}(${field.type} ${field.name}) {
		setValueTrans("${field.name}", ${field.name});
	}
	<#else>
	<#-- *************布尔类型进行特殊处理 独有的属性调用函数*********** -->
	public ${field.type} is${field.name?cap_first}() {
		return getValueTrans("${field.name}");
	}

	public void set${field.name?cap_first}(${field.type} ${field.name}) {
		setValueTrans("${field.name}", ${field.name});
	}
	</#if>
</#list>
</#macro>
