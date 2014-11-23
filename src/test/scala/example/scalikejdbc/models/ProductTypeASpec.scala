package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class ProductTypeASpec extends Specification {

  "ProductTypeA" should {

    val pta = ProductTypeA.syntax("pta")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ProductTypeA.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ProductTypeA.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ProductTypeA.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = ProductTypeA.findAllBy(sqls.eq(pta.productId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ProductTypeA.countBy(sqls.eq(pta.productId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ProductTypeA.create(productId = 123, aName = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ProductTypeA.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ProductTypeA.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ProductTypeA.findAll().head
      ProductTypeA.destroy(entity)
      val shouldBeNone = ProductTypeA.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        