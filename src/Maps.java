import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Maps
 */
public class Maps extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String latlon;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("chalra miya");
		URL url;
		String response1;
		try {
			url = new URL("https://api.twitter.com/1/users/show.json?screen_name=expert8385&include_entities=true");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			System.out.println(connection);
			if(connection==null){
				System.out.println("unsuccessful");
			}
			StringBuilder sb=new StringBuilder("");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()), 10000);
				String strLine = null;
				while ((strLine = input.readLine()) != null) {
					sb.append(strLine);
				}
				input.close();
			}
			response1 = sb.toString();
			connection.disconnect();
			System.out.println(response1);
			JSONObject obj=JSONObject.fromObject(response1);
			String o=obj.getString("location");
			System.out.println(o);
			URL url1 = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="+o+"&sensor=true");
			HttpURLConnection connection1 =(HttpURLConnection) url1.openConnection();
			System.out.println(connection1);
			StringBuilder sbb=new StringBuilder("");
			if (connection1.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader input1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()), 10000);
				String stringLine = null;
				while ((stringLine = input1.readLine()) != null) {
					sbb.append(stringLine);
				}
				input1.close();
			}
			latlon = sbb.toString();
			connection1.disconnect();
			JSONObject ll=JSONObject.fromObject(latlon);
			//System.out.println(ll);
			JSONArray array = ll.getJSONArray("results");
			JSONObject obje = array.getJSONObject(0);
			JSONObject l2= obje.getJSONObject("geometry");
			JSONObject object=l2.getJSONObject("location");
			String oo=object.getString("lng");
			System.out.println("Longitude="+oo);
			String o1=object.getString("lat");
			System.out.println("latitude"+o1);
			response.sendRedirect("http://maps.google.com/maps?z=15&t=m&q=loc:"+o1+"+"+oo);
		}
		catch(Exception e){
			System.out.println();
		}

	}

}
