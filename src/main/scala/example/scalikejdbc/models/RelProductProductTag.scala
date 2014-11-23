package example.scalikejdbc.models

import scalikejdbc._

case class RelProductProductTag(
  productId: Int, 
  productTagId: Int) {

  def save()(implicit session: DBSession = RelProductProductTag.autoSession): RelProductProductTag = RelProductProductTag.save(this)(session)

  def destroy()(implicit session: DBSession = RelProductProductTag.autoSession): Unit = RelProductProductTag.destroy(this)(session)

}
      

object RelProductProductTag extends SQLSyntaxSupport[RelProductProductTag] {

  override val schemaName = Some("orm_test")

  override val tableName = "rel_product_product_tag"

  override val columns = Seq("product_id", "product_tag_id")

  def apply(rppt: SyntaxProvider[RelProductProductTag])(rs: WrappedResultSet): RelProductProductTag = apply(rppt.resultName)(rs)
  def apply(rppt: ResultName[RelProductProductTag])(rs: WrappedResultSet): RelProductProductTag = new RelProductProductTag(
    productId = rs.get(rppt.productId),
    productTagId = rs.get(rppt.productTagId)
  )
      
  val rppt = RelProductProductTag.syntax("rppt")

  override val autoSession = AutoSession

  def find(productId: Int, productTagId: Int)(implicit session: DBSession = autoSession): Option[RelProductProductTag] = {
    withSQL {
      select.from(RelProductProductTag as rppt).where.eq(rppt.productId, productId).and.eq(rppt.productTagId, productTagId)
    }.map(RelProductProductTag(rppt.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[RelProductProductTag] = {
    withSQL(select.from(RelProductProductTag as rppt)).map(RelProductProductTag(rppt.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(RelProductProductTag as rppt)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[RelProductProductTag] = {
    withSQL { 
      select.from(RelProductProductTag as rppt).where.append(sqls"${where}")
    }.map(RelProductProductTag(rppt.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(RelProductProductTag as rppt).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    productTagId: Int)(implicit session: DBSession = autoSession): RelProductProductTag = {
    withSQL {
      insert.into(RelProductProductTag).columns(
        column.productId,
        column.productTagId
      ).values(
        productId,
        productTagId
      )
    }.update.apply()

    RelProductProductTag(
      productId = productId,
      productTagId = productTagId)
  }

  def save(entity: RelProductProductTag)(implicit session: DBSession = autoSession): RelProductProductTag = {
    withSQL {
      update(RelProductProductTag).set(
        column.productId -> entity.productId,
        column.productTagId -> entity.productTagId
      ).where.eq(column.productId, entity.productId).and.eq(column.productTagId, entity.productTagId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: RelProductProductTag)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(RelProductProductTag).where.eq(column.productId, entity.productId).and.eq(column.productTagId, entity.productTagId) }.update.apply()
  }
        
}
