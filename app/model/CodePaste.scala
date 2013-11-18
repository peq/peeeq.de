package model

import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession
import java.sql.Date
import scala.slick.direct._
import scala.slick.direct.AnnotationMapper._
import scala.reflect.runtime.universe
import scala.slick.jdbc.StaticQuery.interpolation
import org.joda.time.DateTime
import com.github.tototoshi.slick.JodaSupport._

/*
@table("mapping_nopaste_jass") case class CodePaste(
  @column("id") id: Long,
  @column("jass_code") jass_code: String
)
*/

case class CodePaste(
	id: Int, 
	code: String,
	datum: DateTime,
	userid: Int,
	codetype: String,
	key: String
)

object CodePastes extends Table[CodePaste]("mapping_nopaste_jass") {
	def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
	def jass_code = column[String]("jass_code")
	def datum = column[DateTime]("datum")
	def userid = column[Int]("userid")
	def codetype = column[String]("codetype")
	def key = column[String]("key")
	
	def * = id ~ jass_code ~ datum ~ userid ~ codetype ~ key <> (CodePaste, CodePaste.unapply _)
	
//	def forInsert = jass_code ~ userid ~ codetype ~ key <> 
//			({ t => CodePaste(None, t._1, None, t._2, t._3, t._4)}, 
//			    { (p: CodePaste) => Some((p.code, p.userid, p.codetype, p.key))})
			
	def autoInc = jass_code ~ userid ~ codetype ~ key  returning id
}
