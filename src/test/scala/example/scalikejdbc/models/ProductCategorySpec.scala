package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class ProductCategorySpec extends Specification {

  "ProductCategory" should {

    val pc = ProductCategory.syntax("pc")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ProductCategory.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ProductCategory.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ProductCategory.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = ProductCategory.findAllBy(sqls.eq(pc.categoryId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ProductCategory.countBy(sqls.eq(pc.categoryId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ProductCategory.create(categoryId = 123, productCategoryName = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ProductCategory.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ProductCategory.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ProductCategory.findAll().head
      ProductCategory.destroy(entity)
      val shouldBeNone = ProductCategory.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        