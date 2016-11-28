<#assign itemCount = 0/>
<table border=0 width="80%">
<#if parameters.list?exists>
    <tr>
    <@s.iterator value="parameters.list">
        <#assign itemCount = itemCount + 1/>
        <#if parameters.listKey?exists>
            <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
        </#if>
        <#if parameters.listValue?exists>
            <#assign itemValue = stack.findString(parameters.listValue)/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
<#assign itemKeyStr=itemKey.toString() />
      <td width="24%">
<input type="checkbox" name="${parameters.name?html}" value="${itemKeyStr?html}" id="${parameters.name?html}-${itemCount}"<#rt/>
<#if parameters.nameValue?exists>
 <#list parameters.nameValue as user>
	<#if user = itemKey>
 		checked="checked"<#rt/>
  	</#if>
</#list>
</#if>
        <#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
        </#if>
        <#if parameters.title?exists>
 title="${parameters.title?html}"<#rt/>
        </#if>
        <#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
        </#if>
        <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
        <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
/>

<span ondblclick="editspecUI(this)" id="${itemKeyStr?html}">${itemValue?html}</span>
&nbsp;&nbsp;
</td>
 <#if itemCount%4 == 0>
 </tr><tr>
 </#if>
  </@s.iterator>
<#else>
  <tr><td>&nbsp;</td></tr>
</#if>
</table>
