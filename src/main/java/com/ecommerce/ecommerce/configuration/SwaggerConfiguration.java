package com.ecommerce.ecommerce.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI springEcommerceOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
					.title("Aplicação de Ecommerce")
					.description("Projeto Desenvolvido para o curso de Desenvolvedor Web Java Full-stack da Digital House")
					.version("v.0.1")
				.license(new License()
					.name("LinkedIn")
					.url("https://www.linkedin.com/in/anakelvia-coutinho/"))
				.contact(new Contact()
					.name("E-mail")
					.email("kelvia_cou@hotmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("GitHub")
					.url("http://github.com/keelcoutinho"));
	}
	
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser()
	{
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach( operation ->			
			{
				ApiResponses apiResponses = operation.getResponses();
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso !!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação"));	
			}));			
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}

}
