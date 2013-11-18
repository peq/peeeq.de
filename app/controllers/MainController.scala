package controllers

import play.api.mvc.{Action, Controller}
import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession
import scala.slick.direct._
import scala.slick.direct.AnnotationMapper._
import scala.reflect.runtime.universe
import scala.slick.jdbc.StaticQuery.interpolation
import model.CodePaste
import scala.slick.driver.MySQLDriver
import play.api.db._
import dbhelper.peqDB
import model.CodePastes

object MainController extends Controller {

	def index = Action {
		Ok(views.html.index.render("Hello World"))
	}

}