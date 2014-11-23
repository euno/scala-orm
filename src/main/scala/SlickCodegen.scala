import scala.slick.codegen.SourceCodeGenerator

/**
 * Created by a12043 on 2014/11/23.
 */
object SlickCodegen {

  def main(args: Array[String]) {
    val slickDriver = "scala.slick.driver.MySQLDriver"
    val jdbcDriver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/orm_test?useUnicode=true&characterEncoding=UTF-8"
    val output = "src/main/scala"
    val pkg = "example.slick.models"
    val user = "root"
    val password = ""

    SourceCodeGenerator.main(
      Array(
        slickDriver, jdbcDriver, url, output, pkg, user, password
      )
    )
  }
}
