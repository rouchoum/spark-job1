package com.project.med.storage.hdfs

import java.text.SimpleDateFormat


object csvToParquet {
  import Metadata._


}
object Metadata extends App{
  sealed trait FieldType {
    def fieldName : String
  }
  case class StringFieldType(fieldName : String) extends FieldType
  case class LongFieldType(fieldName : String) extends FieldType
  case class DateFieldType(fieldName : String, format: Option[SimpleDateFormat]) extends FieldType
  case class IntegerFieldType(fieldName : String) extends FieldType
  case class DoubleFieldType(fieldName: String) extends FieldType

  def parseMetadata(metadata: String, lineSep : String, colSep : String) = { //: Seq[FieldType]={
    metadata
      .split(lineSep)
      .filter(line => line.nonEmpty && line.head != '#')
      .toSeq
      .map(_.split(colSep)).map { line =>
      val fieldName = line(0).trim
      val fieldType = fieldName match {
        case "I_UNIQ_KPI" =>"long"
        case "I_REGRP_KPI" => "long"
        case "I_UNIQ_KPI_JURID_M" => "long"
        case "ID_RP_GA" => "long"
        case "ID_RP_PERS" => "long"
        case "ID_RP_EJ" => "long"
        case "ID_RP_PERS_LG" => "long"
        case _ => line(1).trim.toLowerCase
      }
      fieldType match {
        case "string" => StringFieldType(fieldName)
        case "integer" => IntegerFieldType(fieldName)
        case "long" => LongFieldType(fieldName)
        case "double" => DoubleFieldType(fieldName)
        case "date" =>
          val simpleDateFormat = line(2).trim match {
            case "YYYY-MM-DD" => Some( new SimpleDateFormat("yyyy-MM-dd"))
            case _ => None
          }
          DateFieldType(fieldName, simpleDateFormat)
        case _ => StringFieldType(fieldName)
      }
    }
  }
}
