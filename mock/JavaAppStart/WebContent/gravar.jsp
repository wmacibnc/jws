<?xml version="1.0" encoding="utf-8"?>

<% response.setContentType("application/x-java-jnlp-file"); %>

<jnlp
    codebase="http://52.36.222.170:8080/JavaAppStart/"
    >
    
    <information>
        <title>My First Java Web Start - Desktop</title>
        <vendor>Programando com Java</vendor>
        <homepage href="http://mballem.wordpress.com/"/>
        <description kind="short">
            Sistema biometria
         </description>
        <offline-allowed/>
    </information>
    <security>  
        <all-permissions />  
    </security>
    <resources>
        <j2se version="1.5+"/>
        <jar href="http://52.36.222.170:8080/JavaAppStart/gravar.jar"
                  main="true" />
        <jar href="NBioBSPJNI.jar"
                  main="false" />
    </resources>
    <application-desc
        main-class="com.wp.mb.appdesktop.GravarDigital">
       <argument><%=request.getParameter("param")%></argument>
    </application-desc>  
    <update check="always"/>
</jnlp>