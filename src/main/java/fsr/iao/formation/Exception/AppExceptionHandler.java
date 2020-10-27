package fsr.iao.formation.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import fsr.iao.formation.responses.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value={UserException.class})
 public ResponseEntity<Object>  HandlerUserException(UserException ex,WebRequest request ){
	 ErrorMessage err=new ErrorMessage(new Date(),ex.getMessage());
	 return new ResponseEntity<>(err,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
 }
	@ExceptionHandler(value={AddressException.class})
 public ResponseEntity<Object>  HandlerAdddressException(AddressException ex,WebRequest request ){
	 ErrorMessage err=new ErrorMessage(new Date(),ex.getMessage());
	 return new ResponseEntity<>(err,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
 }
	
	@ExceptionHandler(value={Exception.class})
 public ResponseEntity<Object>  HandlerException(Exception ex,WebRequest request ){
	 ErrorMessage err=new ErrorMessage(new Date(),ex.getMessage());
	 return new ResponseEntity<>(err,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
 }	
	
	
	@ExceptionHandler(value={MethodArgumentNotValidException.class})
 public ResponseEntity<Object>  HandlerException(MethodArgumentNotValidException ex,WebRequest request ){
	 Map<String,String> errors =new HashMap<>();
	 ex.getBindingResult().getFieldErrors().forEach(er->{
		 errors.put(er.getField(), er.getDefaultMessage());
	 });		
	 return new ResponseEntity<>(errors,new HttpHeaders(),HttpStatus.BAD_REQUEST);
 }
}
