<#import "parts/common.ftl" as c>
<#import "parts/maclogin.ftl" as l>
<@c.page>

<h3><i>Добавление нового пользователя</i></h3>
${message?if_exists}
   <@l.maclogin "/registration" />

</@c.page>