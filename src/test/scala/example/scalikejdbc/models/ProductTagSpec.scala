package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class ProductTagSpec extends Specification {

  "ProductTag" should {

    val pt = ProductTag.syntax("pt")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ProductTag.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ProductTag.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ProductTag.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = ProductTag.findAllBy(sqls.eq(pt.productTagId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ProductTag.countBy(sqls.eq(pt.productTagId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ProductTag.create(productTagId = 123, productTagName = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ProductTag.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ProductTag.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ProductTag.findAll().head
      ProductTag.destroy(entity)
      val shouldBeNone = ProductTag.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        