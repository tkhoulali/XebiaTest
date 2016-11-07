package com.xebian.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.xebian.impl.Direction;
import com.xebian.impl.Orientation;
import com.xebian.impl.Surface;
import com.xebian.impl.Tondeuse;

/**
 * @author TK
 *
 */
public class FileService {

	public static List<Callable<Tondeuse>> executeFile(String path) throws ServiceException {
		
		if(path == null) throw new ServiceException("File name can't be null", "NULL_FILE_NAME");
		BufferedReader br = null;
		List<Callable<Tondeuse>> taches = new ArrayList<Callable<Tondeuse>>();

		try {
			String line = "";
			Surface s = null;

			br = new BufferedReader(new FileReader(path));

			// Creation de la surface
			if ((line = br.readLine()) != null && !line.isEmpty()) {
				s = createSurface(line);
			} else {
				throw new ServiceException("No surface line detected !! ", "Empty_FILE_EXCEPTION");
			}

			while ((line = br.readLine()) != null) {
				// suppose instructions are always on the next line after
				// Tondeuse line
				String commandes = br.readLine();
				if (commandes == null || commandes.isEmpty()) {
					throw new ServiceException("File format not accepted, no commandes detected after --> " + line, "FILE_Format_EXCEPTION");
				}
				Tondeuse t = createTondeuse(line, commandes, s);
				taches.add(t);
			}

		} catch (FileNotFoundException e) {
			 throw new ServiceException(e.getMessage(),"FILE_NOT_FOUND_EXCEPTION");
		} catch (IOException e) {
			throw new ServiceException(e.getMessage(),"FILE_INPUT_OUTPUT_EXCEPTION");
		} finally {
			if (br != null) {
				try {
					if(br!=null)
						br.close();
				} catch (IOException e) {
					throw new ServiceException(e.getMessage(),"FILE_CLOSE_EXCEPTION");
				}
			}
		}

		return taches;
	}

	/**
	 * Tondeuse creation
	 * 
	 * @param line
	 * @param commandes
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static Tondeuse createTondeuse(String line, String commandes, Surface s) throws ServiceException {
		String[] tondeuseLine = line.split(" ");
		int xt = Integer.parseInt(tondeuseLine[0]);
		int yt = Integer.parseInt(tondeuseLine[1]);
		Orientation o;

		// check orientation
		if (!isOrientation(tondeuseLine[2])) {
			throw new ServiceException("Orientation :" + tondeuseLine[2] + " is not correct !!","FILE_NOT_FOUND_EXCEPTION");
		} else {
			o = Orientation.valueOf(tondeuseLine[2]);
		}

		// check commandes
		if (!checkCommandes(commandes)) {
			throw new ServiceException("Instructions :" + commandes + " are not correct !!", "FILE_INSTRUCTIONS_EXCEPTION");
		}
		return new Tondeuse(xt, yt, o, s, commandes);
	}

	/**
	 * Surface creation
	 * 
	 * @param line
	 * @return
	 * @throws ServiceException 
	 */
	public static Surface createSurface(String line) throws ServiceException {
		String[] sufaceLine = line.split(" ");
		int x = 0;
		int y = 0;
		try {
			x = Integer.parseInt(sufaceLine[0]);
			y = Integer.parseInt(sufaceLine[1]);
		} catch (NumberFormatException e) {
			throw new ServiceException("params not int detected : "+x+" / "+y,"NUMBER_FORMAT_EXCEPTION");
		}
		return new Surface(x, y);
		
	}

	public static boolean checkCommandes(String line) {
		String[] commandes = line.split("");

		for (String str : commandes) {
			if (!isDirection(str))
				return false;
		}
		return true;
	}

	public static boolean isDirection(String test) {

		for (Direction d : Direction.values()) {
			if (d.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOrientation(String test) {

		for (Orientation o : Orientation.values()) {
			if (o.name().equals(test)) {
				return true;
			}
		}
		return false;
	}
}
