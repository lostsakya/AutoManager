package edu.buaa.automanager.service;


public interface SocketListener {
	public void onSocketStart(String string, String msg);

	public void onSocketSuccess();

	public void onSocketError(String string, String msg, Exception e);

}
