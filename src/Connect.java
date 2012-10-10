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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    void OAuthLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			String consumerSecret="uPIMNJwCMtK09B3BgiBmhuqC7NMzKMVTufzZ4Vc";
			String consumerKey="J2J40q3rF3JPu2lvpSGyw";
			Twitter twitter = new TwitterFactory().getInstance();
			
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		System.out.println(requestToken);
		String auth = requestToken.getAuthenticationURL();
			System.out.println(auth);
		
		request.getSession().setAttribute("requestToken", requestToken);

			String token=requestToken.getToken();
		String tokenSecret=requestToken.getTokenSecret();
			System.out.println(token+"...."+tokenSecret);
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

	
