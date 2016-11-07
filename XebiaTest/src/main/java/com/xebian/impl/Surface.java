package com.xebian.impl;

import com.xebian.services.ServiceException;

/**
 * @author TK
 */
public class Surface {

	int matrice[][];

	public Surface(int ligne, int colonne) throws ServiceException {
		try {
			this.matrice = new int[ligne][colonne];
		} catch (IllegalArgumentException e) {
			throw new ServiceException("invalid params : " + ligne + " / " + colonne, "ILLEGAL_ARGUMENT_EXCEPTION");
		} catch (NegativeArraySizeException e) {
			throw new ServiceException("params should be positive", "NEGATIVE_SIZE_EXCEPTION");
		}
	}

	/**
	 * check if matrice[x][y] is correct
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean checkCase(int x, int y) {
		if (x <= this.matrice.length && y <= this.matrice[0].length)
			return true;
		else
			return false;
	}

}
