package example.scalikejdbc.models

import scalikejdbc._

case class History(
  historyId: Int, 
  productId: Int, 
  quantity: Int, 
  amount: Int) {

  def save()(implicit session: DBSession = History.autoSession): History = History.save(this)(session)

  def destroy()(implicit session: DBSession = History.autoSession): Unit = History.destroy(this)(session)

}
      

object History extends SQLSyntaxSupport[History] {

  override val schemaName = Some("orm_test")

  override val tableName = "history"

  override val columns = Seq("history_id", "product_id", "quantity", "amount")

  def apply(h: SyntaxProvider[History])(rs: WrappedResultSet): History = apply(h.resultName)(rs)
  def apply(h: ResultName[History])(rs: WrappedResultSet): History = new History(
    historyId = rs.get(h.historyId),
    productId = rs.get(h.productId),
    quantity = rs.get(h.quantity),
    amount = rs.get(h.amount)
  )
      
  val h = History.syntax("h")

  override val autoSession = AutoSession

  def find(historyId: Int)(implicit session: DBSession = autoSession): Option[History] = {
    withSQL {
      select.from(History as h).where.eq(h.historyId, historyId)
    }.map(History(h.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[History] = {
    withSQL(select.from(History as h)).map(History(h.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(History as h)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[History] = {
    withSQL { 
      select.from(History as h).where.append(sqls"${where}")
    }.map(History(h.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(History as h).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    quantity: Int,
    amount: Int)(implicit session: DBSession = autoSession): History = {
    val generatedKey = withSQL {
      insert.into(History).columns(
        column.productId,
        column.quantity,
        column.amount
      ).values(
        productId,
        quantity,
        amount
      )
    }.updateAndReturnGeneratedKey.apply()

    History(
      historyId = generatedKey.toInt, 
      productId = productId,
      quantity = quantity,
      amount = amount)
  }

  def save(entity: History)(implicit session: DBSession = autoSession): History = {
    withSQL {
      update(History).set(
        column.historyId -> entity.historyId,
        column.productId -> entity.productId,
        column.quantity -> entity.quantity,
        column.amount -> entity.amount
      ).where.eq(column.historyId, entity.historyId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: History)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(History).where.eq(column.historyId, entity.historyId) }.update.apply()
  }
        
}
