package com.aucklanduni.rmi.banking.client;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Class that implements a Producer. A Producer instance is intended to be run
 * in a thread. The producer uses a parser to read commands from a data file;
 * the commands are used to populate a buffer (in this case a BlockingQueue
 * implementation). The thread that runs the producer terminates after all
 * commands have been placed in the buffer.
 */
public class Producer implements Runnable {
	
	private static final Logger _logger = LoggerFactory.getLogger(Producer.class);
	
	private String fCommandFileName;
	private CommandParser fParser;
	private BlockingQueue<String[]> fQueue;

	public Producer(String commandFileName, CommandParser parser, BlockingQueue<String[]> queue) {
		this.fCommandFileName = commandFileName;
		this.fParser = parser;
		this.fQueue = queue;
	}
	
	public void run() {
		try {
			fParser.parse(fCommandFileName, 0, fQueue);
		} catch(InterruptedException e) {
			_logger.error("Producer thread interrupted while reading data file.");
		} catch(IOException e) {
			_logger.error( "Input/output exception reading from data file." );
		} catch(IllegalSyntaxException e) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Input file contains errors ...");
			
			String[] syntaxErrors = e.getErrors();
			for( int i = 0; i < syntaxErrors.length; i++ ) {
				buffer.append( syntaxErrors[ i ] + "\n"); 
			}
			_logger.error(buffer.toString());
		}
	}
	
}
