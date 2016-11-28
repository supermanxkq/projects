<#if (actionErrors?exists && actionErrors?size > 0)>
	<div class="errormsg">
	<#list actionErrors as error>
		<span class="errorMessage">${error}</span><br/>
	</#list>
	</div>
</#if>
