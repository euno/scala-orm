package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class AccountSpec extends Specification {

  "Account" should {

    val a = Account.syntax("a")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Account.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Account.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Account.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = Account.findAllBy(sqls.eq(a.accountId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Account.countBy(sqls.eq(a.accountId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Account.create(accountName = "MyString", mailAddress = "MyString", activeFlag = false, createdBy = 123, createdOn = DateTime.now, updatedBy = 123, updatedOn = DateTime.now)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Account.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Account.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Account.findAll().head
      Account.destroy(entity)
      val shouldBeNone = Account.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        