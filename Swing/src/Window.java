import javax.swing.JFrame;

public class Window extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5259700796854880162L;
	public Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,480);
        setTitle("�ҵļ��±�");
    }
    public static void main(String[] args) {
           Window win =new Window();
           win.setVisible(true);
    }
}