package jld;
import jld.GUI.*;

import java.awt.EventQueue;
public class CMain{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new wndLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
