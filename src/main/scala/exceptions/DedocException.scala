package exceptions

/**
Raise in case if Dedoc can not handle request
 **/
class DedocException(message: String) extends Exception(message)