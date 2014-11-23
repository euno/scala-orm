package example.slick.models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = History.ddl ++ Product.ddl ++ ProductCategory.ddl ++ ProductTag.ddl ++ ProductTypeA.ddl ++ ProductTypeB.ddl ++ RelProductProductTag.ddl
  
  /** Entity class storing rows of table History
   *  @param historyId Database column history_id DBType(INT), AutoInc, PrimaryKey
   *  @param productId Database column product_id DBType(INT)
   *  @param quantity Database column quantity DBType(INT)
   *  @param amount Database column amount DBType(INT) */
  case class HistoryRow(historyId: Int, productId: Int, quantity: Int, amount: Int)
  /** GetResult implicit for fetching HistoryRow objects using plain SQL queries */
  implicit def GetResultHistoryRow(implicit e0: GR[Int]): GR[HistoryRow] = GR{
    prs => import prs._
    HistoryRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table history. Objects of this class serve as prototypes for rows in queries. */
  class History(_tableTag: Tag) extends Table[HistoryRow](_tableTag, "history") {
    def * = (historyId, productId, quantity, amount) <> (HistoryRow.tupled, HistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (historyId.?, productId.?, quantity.?, amount.?).shaped.<>({r=>import r._; _1.map(_=> HistoryRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column history_id DBType(INT), AutoInc, PrimaryKey */
    val historyId: Column[Int] = column[Int]("history_id", O.AutoInc, O.PrimaryKey)
    /** Database column product_id DBType(INT) */
    val productId: Column[Int] = column[Int]("product_id")
    /** Database column quantity DBType(INT) */
    val quantity: Column[Int] = column[Int]("quantity")
    /** Database column amount DBType(INT) */
    val amount: Column[Int] = column[Int]("amount")
    
    /** Foreign key referencing Product (database name history_ibfk_1) */
    lazy val productFk = foreignKey("history_ibfk_1", productId, Product)(r => r.productId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table History */
  lazy val History = new TableQuery(tag => new History(tag))
  
  /** Entity class storing rows of table Product
   *  @param productId Database column product_id DBType(INT), PrimaryKey
   *  @param categoryId Database column category_id DBType(INT)
   *  @param productName Database column product_name DBType(VARCHAR), Length(32,true)
   *  @param productType Database column product_type DBType(INT) */
  case class ProductRow(productId: Int, categoryId: Int, productName: String, productType: Int)
  /** GetResult implicit for fetching ProductRow objects using plain SQL queries */
  implicit def GetResultProductRow(implicit e0: GR[Int], e1: GR[String]): GR[ProductRow] = GR{
    prs => import prs._
    ProductRow.tupled((<<[Int], <<[Int], <<[String], <<[Int]))
  }
  /** Table description of table product. Objects of this class serve as prototypes for rows in queries. */
  class Product(_tableTag: Tag) extends Table[ProductRow](_tableTag, "product") {
    def * = (productId, categoryId, productName, productType) <> (ProductRow.tupled, ProductRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (productId.?, categoryId.?, productName.?, productType.?).shaped.<>({r=>import r._; _1.map(_=> ProductRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column product_id DBType(INT), PrimaryKey */
    val productId: Column[Int] = column[Int]("product_id", O.PrimaryKey)
    /** Database column category_id DBType(INT) */
    val categoryId: Column[Int] = column[Int]("category_id")
    /** Database column product_name DBType(VARCHAR), Length(32,true) */
    val productName: Column[String] = column[String]("product_name", O.Length(32,varying=true))
    /** Database column product_type DBType(INT) */
    val productType: Column[Int] = column[Int]("product_type")
    
    /** Foreign key referencing ProductCategory (database name product_ibfk_1) */
    lazy val productCategoryFk = foreignKey("product_ibfk_1", categoryId, ProductCategory)(r => r.categoryId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Product */
  lazy val Product = new TableQuery(tag => new Product(tag))
  
  /** Entity class storing rows of table ProductCategory
   *  @param categoryId Database column category_id DBType(INT), PrimaryKey
   *  @param productCategoryName Database column product_category_name DBType(VARCHAR), Length(32,true) */
  case class ProductCategoryRow(categoryId: Int, productCategoryName: String)
  /** GetResult implicit for fetching ProductCategoryRow objects using plain SQL queries */
  implicit def GetResultProductCategoryRow(implicit e0: GR[Int], e1: GR[String]): GR[ProductCategoryRow] = GR{
    prs => import prs._
    ProductCategoryRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table product_category. Objects of this class serve as prototypes for rows in queries. */
  class ProductCategory(_tableTag: Tag) extends Table[ProductCategoryRow](_tableTag, "product_category") {
    def * = (categoryId, productCategoryName) <> (ProductCategoryRow.tupled, ProductCategoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (categoryId.?, productCategoryName.?).shaped.<>({r=>import r._; _1.map(_=> ProductCategoryRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column category_id DBType(INT), PrimaryKey */
    val categoryId: Column[Int] = column[Int]("category_id", O.PrimaryKey)
    /** Database column product_category_name DBType(VARCHAR), Length(32,true) */
    val productCategoryName: Column[String] = column[String]("product_category_name", O.Length(32,varying=true))
  }
  /** Collection-like TableQuery object for table ProductCategory */
  lazy val ProductCategory = new TableQuery(tag => new ProductCategory(tag))
  
  /** Entity class storing rows of table ProductTag
   *  @param productTagId Database column product_tag_id DBType(INT), PrimaryKey
   *  @param productTagName Database column product_tag_name DBType(VARCHAR), Length(32,true) */
  case class ProductTagRow(productTagId: Int, productTagName: String)
  /** GetResult implicit for fetching ProductTagRow objects using plain SQL queries */
  implicit def GetResultProductTagRow(implicit e0: GR[Int], e1: GR[String]): GR[ProductTagRow] = GR{
    prs => import prs._
    ProductTagRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table product_tag. Objects of this class serve as prototypes for rows in queries. */
  class ProductTag(_tableTag: Tag) extends Table[ProductTagRow](_tableTag, "product_tag") {
    def * = (productTagId, productTagName) <> (ProductTagRow.tupled, ProductTagRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (productTagId.?, productTagName.?).shaped.<>({r=>import r._; _1.map(_=> ProductTagRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column product_tag_id DBType(INT), PrimaryKey */
    val productTagId: Column[Int] = column[Int]("product_tag_id", O.PrimaryKey)
    /** Database column product_tag_name DBType(VARCHAR), Length(32,true) */
    val productTagName: Column[String] = column[String]("product_tag_name", O.Length(32,varying=true))
  }
  /** Collection-like TableQuery object for table ProductTag */
  lazy val ProductTag = new TableQuery(tag => new ProductTag(tag))
  
  /** Entity class storing rows of table ProductTypeA
   *  @param productId Database column product_id DBType(INT), PrimaryKey
   *  @param aName Database column a_name DBType(VARCHAR), Length(32,true) */
  case class ProductTypeARow(productId: Int, aName: String)
  /** GetResult implicit for fetching ProductTypeARow objects using plain SQL queries */
  implicit def GetResultProductTypeARow(implicit e0: GR[Int], e1: GR[String]): GR[ProductTypeARow] = GR{
    prs => import prs._
    ProductTypeARow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table product_type_a. Objects of this class serve as prototypes for rows in queries. */
  class ProductTypeA(_tableTag: Tag) extends Table[ProductTypeARow](_tableTag, "product_type_a") {
    def * = (productId, aName) <> (ProductTypeARow.tupled, ProductTypeARow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (productId.?, aName.?).shaped.<>({r=>import r._; _1.map(_=> ProductTypeARow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column product_id DBType(INT), PrimaryKey */
    val productId: Column[Int] = column[Int]("product_id", O.PrimaryKey)
    /** Database column a_name DBType(VARCHAR), Length(32,true) */
    val aName: Column[String] = column[String]("a_name", O.Length(32,varying=true))
    
    /** Foreign key referencing Product (database name product_type_a_ibfk_1) */
    lazy val productFk = foreignKey("product_type_a_ibfk_1", productId, Product)(r => r.productId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ProductTypeA */
  lazy val ProductTypeA = new TableQuery(tag => new ProductTypeA(tag))
  
  /** Entity class storing rows of table ProductTypeB
   *  @param productId Database column product_id DBType(INT), PrimaryKey
   *  @param bCount Database column b_count DBType(INT) */
  case class ProductTypeBRow(productId: Int, bCount: Int)
  /** GetResult implicit for fetching ProductTypeBRow objects using plain SQL queries */
  implicit def GetResultProductTypeBRow(implicit e0: GR[Int]): GR[ProductTypeBRow] = GR{
    prs => import prs._
    ProductTypeBRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table product_type_b. Objects of this class serve as prototypes for rows in queries. */
  class ProductTypeB(_tableTag: Tag) extends Table[ProductTypeBRow](_tableTag, "product_type_b") {
    def * = (productId, bCount) <> (ProductTypeBRow.tupled, ProductTypeBRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (productId.?, bCount.?).shaped.<>({r=>import r._; _1.map(_=> ProductTypeBRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column product_id DBType(INT), PrimaryKey */
    val productId: Column[Int] = column[Int]("product_id", O.PrimaryKey)
    /** Database column b_count DBType(INT) */
    val bCount: Column[Int] = column[Int]("b_count")
    
    /** Foreign key referencing Product (database name product_type_b_ibfk_1) */
    lazy val productFk = foreignKey("product_type_b_ibfk_1", productId, Product)(r => r.productId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ProductTypeB */
  lazy val ProductTypeB = new TableQuery(tag => new ProductTypeB(tag))
  
  /** Entity class storing rows of table RelProductProductTag
   *  @param productId Database column product_id DBType(INT)
   *  @param productTagId Database column product_tag_id DBType(INT) */
  case class RelProductProductTagRow(productId: Int, productTagId: Int)
  /** GetResult implicit for fetching RelProductProductTagRow objects using plain SQL queries */
  implicit def GetResultRelProductProductTagRow(implicit e0: GR[Int]): GR[RelProductProductTagRow] = GR{
    prs => import prs._
    RelProductProductTagRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table rel_product_product_tag. Objects of this class serve as prototypes for rows in queries. */
  class RelProductProductTag(_tableTag: Tag) extends Table[RelProductProductTagRow](_tableTag, "rel_product_product_tag") {
    def * = (productId, productTagId) <> (RelProductProductTagRow.tupled, RelProductProductTagRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (productId.?, productTagId.?).shaped.<>({r=>import r._; _1.map(_=> RelProductProductTagRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column product_id DBType(INT) */
    val productId: Column[Int] = column[Int]("product_id")
    /** Database column product_tag_id DBType(INT) */
    val productTagId: Column[Int] = column[Int]("product_tag_id")
    
    /** Primary key of RelProductProductTag (database name rel_product_product_tag_PK) */
    val pk = primaryKey("rel_product_product_tag_PK", (productId, productTagId))
    
    /** Foreign key referencing Product (database name rel_product_product_tag_ibfk_1) */
    lazy val productFk = foreignKey("rel_product_product_tag_ibfk_1", productId, Product)(r => r.productId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing ProductTag (database name rel_product_product_tag_ibfk_2) */
    lazy val productTagFk = foreignKey("rel_product_product_tag_ibfk_2", productTagId, ProductTag)(r => r.productTagId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table RelProductProductTag */
  lazy val RelProductProductTag = new TableQuery(tag => new RelProductProductTag(tag))
}