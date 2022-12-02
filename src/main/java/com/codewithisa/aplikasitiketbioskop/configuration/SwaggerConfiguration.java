package com.codewithisa.aplikasitiketbioskop.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myApi(@Value("API untuk melakukan pemesanan tiket bioskop. Masing-masing API memiliki " +
            "hak akses yang terbatas. Customer dapat mengupdate user, menghapus user," +
            " dan melakukan pemesanan tiket. Admin dapat menambahkan film baru, mengupdate nama film," +
            " dan menghapus film. Semua user dapat sign up, menampilkan film yang sedang tayang, dan " +
            "menampilkan jadwal dari film tertentu." +
            "\n" +
            "\nUntuk informasi lebih lanjut silahkan menghubungi isarandra@yahoo.com") String appDescription,
                           @Value("v7.0.0") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Bioskop API")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
