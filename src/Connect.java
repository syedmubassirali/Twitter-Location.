import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
/**
 * Servlet implementation class Connect
 */
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
		String latlon;
	String latlon1,screenName;
	String x;
	String y;
	double latitude=0.0,longitude=0.0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    void OAuthLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 Twitter twitter = new TwitterFactory().getInstance();
		String consumerSecret="uPIMNJwCMtK09B3BgiBmhuqC7NMzKMVTufzZ4Vc";
		String consumerKey="J2J40q3rF3JPu2lvpSGyw";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
    	request.getSession().setAttribute("twitter", twitter);
           	try{
		RequestToken requestToken = twitter.getOAuthRequestToken("http://localhost:8080/Locationfinde/Maps");
		System.out.println(requestToken);
		request.getSession().setAttribute("requestToken", requestToken);
		String auth = requestToken.getAuthenticationURL();
			System.out.println(auth);
			response.sendRedirect(auth);		        
		    }
			catch (Exception ex) {
				ex.printStackTrace();
			}
    	 
			}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//twitter
		Connect c =new Connect();
		c.OAuthLogin(request, response);
			}		}

	
