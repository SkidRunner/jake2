/*
 * Jake2.java
 * Copyright (C)  2003
 * 
 * $Id: Jake2.java,v 1.2 2004-07-08 15:58:46 hzi Exp $
 */
/*
Copyright (C) 1997-2001 Id Software, Inc.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

*/
package jake2;

import java.io.IOException;
import java.util.logging.*;

import jake2.qcommon.*;
import jake2.sys.Sys;

/**
 * Jake2 is the main class of Quake2 for Java.
 */
public final class Jake2 {

	// R I S K Y   C O D E   D A T A B A S E
	// ------------------------------------- 
	// (m?gliche Fehlerursachen f?r sp?teres Debuggen)
	// - sicherstellen, dass svs.clients richtig durchnummeriert wird (client_t.serverindex) 
	// - sicherstellen, dass SV_GAME.ge.edicts richtig durchnummeriert wird (ent.s.number der richtige index ?)
	// - CM_DecompressVis() richtig portiert ?
	// - NET.Net_Socket() sockarr_in.addr richtig ersetzt ? 
	// 

	/**
		 * for all other classes it should be:
		 * <code>
		 *   private static Logger logger = Logger.getLogger(<CLASSNAME>.class.getName());
		 * </code>
		 * 
		 */
	private static Logger logger;

	/**
	 * main is used to start the game. Quake2 for Java supports the 
	 * following command line arguments:
	 * @param args
	 */
	public static void main(String[] args) {

		// init the global LogManager with the logging.properties file
		try {
			LogManager.getLogManager().readConfiguration(Jake2.class.getResourceAsStream("/jake2/logging.properties"));
		}
		catch (SecurityException secEx) {
			secEx.printStackTrace();
		}
		catch (IOException ioEx) {
			System.err.println("FATAL Error: can't load /jake2/logging.properties (classpath)");
			ioEx.printStackTrace();
		}

		logger = Logger.getLogger(Jake2.class.getName());

		logger.log(Level.INFO, "Start Jake2 :-)");

		// in C the first arg is the filename
		int argc = (args == null) ? 1 : args.length + 1;
		String[] c_args = new String[argc];
		c_args[0] = "Jake2";
		if (argc > 1) {
			System.arraycopy(args, 0, c_args, 1, argc - 1);
		}
		Qcommon.Init(c_args);

		Globals.nostdout = Cvar.Get("nostdout", "0", 0);

		int oldtime = Sys.Milliseconds();
		int newtime;
		int time;
		while (true) {
			// find time spending rendering last frame
			newtime = Sys.Milliseconds();
			time = newtime - oldtime;

			if (time > 0)
				Qcommon.Frame(time);
			oldtime = newtime;

			// save cpu resources
//			try {
//				Thread.sleep(1);
//			}
//			catch (InterruptedException e) {
//			}
		}
	}
}
