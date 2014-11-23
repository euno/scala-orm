package example.scalikejdbc.models

import scalikejdbc._

case class RelChildProductTag(
  productId: Int, 
  productTagId: Int) {

  def save()(implicit session: DBSession = RelChildProductTag.autoSession): RelChildProductTag = RelChildProductTag.save(this)(session)

  def destroy()(implicit session: DBSession = RelChildProductTag.autoSession): Unit = RelChildProductTag.destroy(this)(session)

}
      

object RelChildProductTag extends SQLSyntaxSupport[RelChildProductTag] {

  override val schemaName = Some("orm_test")

  override val tableName = "rel_child_product_tag"

  override val columns = Seq("product_id", "product_tag_id")

  def apply(rcpt: SyntaxProvider[RelChildProductTag])(rs: WrappedResultSet): RelChildProductTag = apply(rcpt.resultName)(rs)
  def apply(rcpt: ResultName[RelChildProductTag])(rs: WrappedResultSet): RelChildProductTag = new RelChildProductTag(
    productId = rs.get(rcpt.productId),
    productTagId = rs.get(rcpt.productTagId)
  )
      
  val rcpt = RelChildProductTag.syntax("rcpt")

  override val autoSession = AutoSession

  def find(productId: Int, productTagId: Int)(implicit session: DBSession = autoSession): Option[RelChildProductTag] = {
    withSQL {
      select.from(RelChildProductTag as rcpt).where.eq(rcpt.productId, productId).and.eq(rcpt.productTagId, productTagId)
    }.map(RelChildProductTag(rcpt.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[RelChildProductTag] = {
    withSQL(select.from(RelChildProductTag as rcpt)).map(RelChildProductTag(rcpt.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(RelChildProductTag as rcpt)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[RelChildProductTag] = {
    withSQL { 
      select.from(RelChildProductTag as rcpt).where.append(sqls"${where}")
    }.map(RelChildProductTag(rcpt.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(RelChildProductTag as rcpt).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    productTagId: Int)(implicit session: DBSession = autoSession): RelChildProductTag = {
    withSQL {
      insert.into(RelChildProductTag).columns(
        column.productId,
        column.productTagId
      ).values(
        productId,
        productTagId
      )
    }.update.apply()

    RelChildProductTag(
      productId = productId,
      productTagId = productTagId)
  }

  def save(entity: RelChildProductTag)(implicit session: DBSession = autoSession): RelChildProductTag = {
    withSQL {
      update(RelChildProductTag).set(
        column.productId -> entity.productId,
        column.productTagId -> entity.productTagId
      ).where.eq(column.productId, entity.productId).and.eq(column.productTagId, entity.productTagId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: RelChildProductTag)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(RelChildProductTag).where.eq(column.productId, entity.productId).and.eq(column.productTagId, entity.productTagId) }.update.apply()
  }
        
}
