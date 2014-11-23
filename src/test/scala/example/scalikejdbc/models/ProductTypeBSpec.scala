package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class ProductTypeBSpec extends Specification {

  "ProductTypeB" should {

    val ptb = ProductTypeB.syntax("ptb")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ProductTypeB.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ProductTypeB.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ProductTypeB.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = ProductTypeB.findAllBy(sqls.eq(ptb.productId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ProductTypeB.countBy(sqls.eq(ptb.productId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ProductTypeB.create(productId = 123, bCount = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ProductTypeB.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ProductTypeB.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ProductTypeB.findAll().head
      ProductTypeB.destroy(entity)
      val shouldBeNone = ProductTypeB.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        