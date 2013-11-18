package dbhelper

import scala.slick.driver.MySQLDriver.simple._
import play.api.db._



import scala.slick.driver.ExtendedProfile
class DAO(val driver: ExtendedProfile) {
  // Import the query language features from the driver
  import driver.simple._
}

object peqDB {

	def querySingle[S,T](q:Query[S,T]) : Option[T] = {
			withSession { session:Session =>
				q.firstOption(session)
			}    
	}
	
	def queryList[S,T](q:Query[S,T]) : List[T] = {
			withSession { session:Session =>
				q.list()(session)
			}    
	}
	
	def withSession[T](block: (Session)=>T): T = {
			import play.api.Play.current
			val db = Database.forDataSource(DB.getDataSource())
			db.withSession(block)
	}
	
	def withTransaction[T](block: (Session)=>T): T = {
			import play.api.Play.current
			val db = Database.forDataSource(DB.getDataSource())
			db.withTransaction(block)
	}


}