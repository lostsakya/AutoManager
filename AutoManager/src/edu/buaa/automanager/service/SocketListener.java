package edu.buaa.automanager.service;

public interface SocketListener {
	public void onSocketStart();

	public void onSocketSuccess(int action, String msg);

	public void onSocketError(int action, Exception e);

}
