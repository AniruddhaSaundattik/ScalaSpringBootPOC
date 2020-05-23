package tasklist

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration}
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class ApplicationS

object ApplicationO extends App{
  SpringApplication.run(classOf[ApplicationS])
}
