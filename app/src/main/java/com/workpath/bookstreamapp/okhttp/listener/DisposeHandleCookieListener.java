package com.workpath.bookstreamapp.okhttp.listener;

import java.util.ArrayList;

/**********************************************************
 * @function：DisposeHandleCookieListener.java
 **********************************************************/
public interface DisposeHandleCookieListener extends DisposeDataListener
{
	public void onCookie(ArrayList<String> cookieStrLists);
}
