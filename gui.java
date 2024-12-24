import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class gui extends JFrame
{
	static Connection connection = null;
	public static void main(String[] args)
	{
       String url = args[0];
       String username = args[1];
       String password = args[2];
       try
       {
			Class.forName(args[3]);
			System.out.println("No Driver Error");
		}
       catch (ClassNotFoundException e)
       {
			System.out.println("Driver Error!");
			e.printStackTrace();
		}
       try
       {
    	   connection = DriverManager.getConnection(url, username, password);
       }
       catch (SQLException e)
       {
           System.out.println("Connection failed!");
           e.printStackTrace();
       }
		gui g = new gui();
		g.startGUI();
	}
	
	public void startGUI()
	{
       setTitle("Database Manager");
       setSize(450, 300);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setResizable(false);
		setBackground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
      
		JLabel lblNewLabel = new JLabel("Database Manager");
		lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(96, 41, 242, 206);
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(4,1));
		
		JButton insert = new JButton("Insert Author");
		insert.setForeground(Color.BLACK);
		insert.setBackground(Color.WHITE);
		insert.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		insert.setFocusPainted(false);
		panel.add(insert);
		
		JButton update = new JButton("Update Book");
		update.setBackground(Color.WHITE);
		update.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		update.setFocusPainted(false);
		panel.add(update);
		
		JButton delete = new JButton("Delete Author");
		delete.setBackground(Color.WHITE);
		delete.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		delete.setFocusPainted(false);
		panel.add(delete);
		
		JButton queryingFunc = new JButton("Querying Functions");
		queryingFunc.setBackground(Color.WHITE);
		queryingFunc.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		queryingFunc.setFocusPainted(false);
		panel.add(queryingFunc);
		
		setVisible(true);
		
		insert.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
               openInsertFrame();
               setVisible(false);
           }
       });
		update.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	openUpdateFrame();
               setVisible(false);
           }
       });
		delete.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
               openDeleteFrame();
               setVisible(false);
           }
       });
		queryingFunc.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	queryingFunc();
               setVisible(false);
           }
       });
		addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
	}
	public void openInsertFrame()
	{
       JFrame insertFrame = new JFrame("Insert Author");
       insertFrame.setSize(450, 300);
       insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       insertFrame.setLocationRelativeTo(null);
       insertFrame.setResizable(false);
       insertFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("Enter Author Details:");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
       insertFrame.add(lblNewLabel);
       JTextField textField = new JTextField();
       textField.setBounds(20, 50, 400, 35);
       textField.setForeground(Color.BLACK);
       textField.setBackground(Color.WHITE);
       textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       insertFrame.add(textField);
       textField.addFocusListener((new FocusListener()
       {
           @Override
           public void focusLost(FocusEvent e)
           {
               if(textField.getText().isEmpty())
               {
               	textField.setText("Example: 2006 ‘J.K’ ‘Rowling’ 1965");
               }
           }
           @Override
           public void focusGained(FocusEvent e)
           {
           	if(textField.getText().equals("Example: 2006 ‘J.K’ ‘Rowling’ 1965"))
               {
               	textField.setText("");
               }
           }
       }));
       JButton enterButton = new JButton("Enter");
       enterButton.setBounds(96, 90, 242, 51);
       enterButton.setBackground(Color.WHITE);
       enterButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       enterButton.setFocusPainted(false);
       insertFrame.add(enterButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       insertFrame.add(backButton);
      
       insertFrame.setVisible(true);
      
       insertFrame.addWindowListener(new java.awt.event.WindowAdapter()
       {
           public void windowOpened(java.awt.event.WindowEvent evt)
           {
               backButton.requestFocus();
           }
       });
      
       enterButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	String text = textField.getText();
           	String[] insertInput = parseString(text);
           	insertInput[1] = insertInput[1].replace("'", "");
           	insertInput[2] = insertInput[2].replace("'", "");
           	String message = "Words entered:\n";
           	for (String s : insertInput)
           	{
                   message = message + s+"\n";
            }
           	try
           	{
           	    String sql = "INSERT INTO AUTHOR (AuthorID, FirstName, LastName, DOB) VALUES (?, ?, ?, ?)";
           	    PreparedStatement statement = connection.prepareStatement(sql);
           	    statement.setString(1, insertInput[0]);
           	    statement.setString(2, insertInput[1]);
           	    statement.setString(3, insertInput[2]);
           	    statement.setString(4, insertInput[3]);
           	    int rowsInserted = statement.executeUpdate();
           	    if (rowsInserted > 0)
           	    {
           	        displayTable(connection, "author");
           	    }
           	}
           	catch (SQLException ex)
           	{
           	    ex.printStackTrace();
           	    JOptionPane.showMessageDialog(insertFrame, "Error occurred while inserting data");
           	}
           }
       });
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               insertFrame.dispose();
               startGUI();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	public static String[] parseString(String input)
	{
		ArrayList<String> matches = new ArrayList<>();
		Matcher m = Pattern.compile("([^\"']\\S*|\".+?\"|'.+?')\\s*").matcher(input);
		while(m.find())
		{
			matches.add(m.group(1));
		}
		return matches.toArray(new String[0]);
	}
	
	public void openUpdateFrame()
	{
       JFrame updateFrame = new JFrame("Update Book");
       updateFrame.setSize(450, 300);
       updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       updateFrame.setLocationRelativeTo(null);
       updateFrame.setResizable(false);
       updateFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("Enter Book Details:");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		updateFrame.add(lblNewLabel);
       JTextField textField = new JTextField();
       textField.setBounds(20, 50, 400, 35);
       textField.setForeground(Color.BLACK);
       textField.setBackground(Color.WHITE);
       textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       updateFrame.add(textField);
       textField.addFocusListener((new FocusListener()
       {
           @Override
           public void focusLost(FocusEvent e)
           {
               if(textField.getText().isEmpty())
               {
               	textField.setText("Example: 1005 'Red Dragon'");
               }
           }
           @Override
           public void focusGained(FocusEvent e)
           {
               if(textField.getText().equals("Example: 1005 'Red Dragon'"))
               {
               	textField.setText("");
               }
           }
       }));
       JButton enterButton = new JButton("Enter");
       enterButton.setBounds(96, 90, 242, 51);
       enterButton.setBackground(Color.WHITE);
       enterButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       enterButton.setFocusPainted(false);
       updateFrame.add(enterButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       updateFrame.add(backButton);
      
       updateFrame.setVisible(true);
      
       updateFrame.addWindowListener(new java.awt.event.WindowAdapter()
       {
           public void windowOpened(java.awt.event.WindowEvent evt)
           {
               backButton.requestFocus();
           }
       });
      
       enterButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	String text = textField.getText();
           	String[] updateInput = parseString(text);
           	updateInput[1] = updateInput[1].replace("'", "");
           	
               try
               {
                   String sql = "UPDATE BOOK SET Title = ? WHERE BookID = ?";
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setString(1, updateInput[1]);
                   statement.setInt(2, Integer.parseInt(updateInput[0]));
                   int rowsUpdated = statement.executeUpdate();
                   if (rowsUpdated > 0)
                   {
                       displayTable(connection, "book");
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(updateFrame, "No such book found");
                   }
               }
               catch (SQLException ex)
               {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(updateFrame, "Error occurred while updating book");
               }
           }
       });
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	updateFrame.dispose();
               startGUI();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	public void openDeleteFrame()
	{
       JFrame deleteFrame = new JFrame("Delete Author");
       deleteFrame.setSize(450, 300);
       deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       deleteFrame.setLocationRelativeTo(null);
       deleteFrame.setResizable(false);
       deleteFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("Enter Author Details:");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		deleteFrame.add(lblNewLabel);
       JTextField textField = new JTextField();
       textField.setBounds(20, 50, 400, 35);
       textField.setForeground(Color.BLACK);
       textField.setBackground(Color.WHITE);
       textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       deleteFrame.add(textField);
       textField.addFocusListener((new FocusListener()
       {
           @Override
           public void focusLost(FocusEvent e)
           {
               if(textField.getText().isEmpty())
               {
               	textField.setText("Example: 2005");
               }
           }
           @Override
           public void focusGained(FocusEvent e)
           {
               if(textField.getText().equals("Example: 2005"))
               {
               	textField.setText("");
               }
           }
       }));
       JButton enterButton = new JButton("Enter");
       enterButton.setBounds(96, 90, 242, 51);
       enterButton.setBackground(Color.WHITE);
       enterButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       enterButton.setFocusPainted(false);
       deleteFrame.add(enterButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       deleteFrame.add(backButton);
      
       deleteFrame.setVisible(true);
      
       deleteFrame.addWindowListener(new java.awt.event.WindowAdapter()
       {
           public void windowOpened(java.awt.event.WindowEvent evt)
           {
               backButton.requestFocus();
           }
       });
      
       enterButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               String text = textField.getText();
               String[] deleteInput = text.split("\\s+");
               StringBuilder message = new StringBuilder("Words entered:\n");
               for (String word : deleteInput)
               {
                   message.append(word).append("\n");
               }
               try
               {
                   String sql = "DELETE FROM AUTHOR WHERE AuthorID = ?";
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setInt(1, Integer.parseInt(deleteInput[0]));
                   int rowsDeleted = statement.executeUpdate();
                   if (rowsDeleted > 0)
                   {
                       displayTable(connection, "author");
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(deleteFrame, "No Author under that ID found");
                   }
               }
               catch (SQLException ex)
               {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(deleteFrame, "Error occurred while deleting Author");
               }
           }
       });
      
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	deleteFrame.dispose();
               startGUI();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	public void queryingFunc()
	{
		JFrame qfFrame = new JFrame();
		qfFrame.setTitle("Querying Functions");
		qfFrame.setSize(450, 300);
		qfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qfFrame.setLocationRelativeTo(null);
		qfFrame.setResizable(false);
		qfFrame.setBackground(Color.BLACK);
		qfFrame.getContentPane().setBackground(Color.WHITE);
		qfFrame.getContentPane().setLayout(null);
      
		JLabel lblNewLabel = new JLabel("Querying Functions");
		lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		qfFrame.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(96, 41, 242, 206);
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		qfFrame.add(panel);
		panel.setLayout(new GridLayout(4,1));
		
		JButton lBATA = new JButton("List Books and Their Authors");
		lBATA.setForeground(Color.BLACK);
		lBATA.setBackground(Color.WHITE);
		lBATA.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lBATA.setFocusPainted(false);
		panel.add(lBATA);
		
		JButton lRFSB = new JButton("List Reviews From Specific Book");
		lRFSB.setBackground(Color.WHITE);
		lRFSB.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lRFSB.setFocusPainted(false);
		panel.add(lRFSB);
		
		JButton lBFSA = new JButton("List Books From Specific Author");
		lBFSA.setBackground(Color.WHITE);
		lBFSA.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lBFSA.setFocusPainted(false);
		panel.add(lBFSA);
		
		JButton qfback = new JButton("Back");
		qfback.setBackground(Color.WHITE);
		qfback.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		qfback.setFocusPainted(false);
		panel.add(qfback);
		
		qfFrame.setVisible(true);
		
		lBATA.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	lBATA();
           	qfFrame.dispose();
           }
       });
		lRFSB.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	lRFSB();
           	qfFrame.dispose();
           }
       });
		lBFSA.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	lBFSA();
           	qfFrame.dispose();
           }
       });
		qfback.addActionListener(new ActionListener()
		{
           public void actionPerformed(ActionEvent e)
           {
           	startGUI();
               qfFrame.dispose();
           }
       });
		addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
	}
	
	public void lBATA()
	{
       JFrame lBATAFrame = new JFrame("List Books and Their Authors");
       lBATAFrame.setSize(450, 300);
       lBATAFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       lBATAFrame.setLocationRelativeTo(null);
       lBATAFrame.setResizable(false);
       lBATAFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("List Books and Their Authors");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		lBATAFrame.add(lblNewLabel);
       JButton listButton = new JButton("List");
       listButton.setBounds(96, 90, 242, 51);
       listButton.setBackground(Color.WHITE);
       listButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       listButton.setFocusPainted(false);
       lBATAFrame.add(listButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       lBATAFrame.add(backButton);
      
       lBATAFrame.setVisible(true);
      
       listButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               try {
                   StringBuilder message = new StringBuilder("Books and Authors:\n");
                   String bookQuery = "SELECT b.Title AS BookTitle, a.FirstName AS AuthorFirstName, a.LastName AS AuthorLastName " +
                                      "FROM BOOK b " +
                                      "INNER JOIN AUTHOR a ON b.AuthorID = a.AuthorID";
                   Statement statement = connection.createStatement();
                   ResultSet resultSet = statement.executeQuery(bookQuery);
                   while (resultSet.next())
                   {
                       String title = resultSet.getString("BookTitle");
                       String authorFirstName = resultSet.getString("AuthorFirstName");
                       String authorLastName = resultSet.getString("AuthorLastName");
                       message.append("Title: ").append(title).append(", Author: ").append(authorFirstName).append(" ").append(authorLastName).append("\n");
                   }
                   resultSet.close();
                   statement.close();
                   JOptionPane.showMessageDialog(lBATAFrame, message.toString());
               }
               catch (SQLException ex)
               {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(lBATAFrame, "Error occurred while listing books and authors");
               }
           }
       });
      
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	lBATAFrame.dispose();
               queryingFunc();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	public void lRFSB()
	{
       JFrame lRFSBFrame = new JFrame("List Reviews From Specific Book");
       lRFSBFrame.setSize(450, 300);
       lRFSBFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       lRFSBFrame.setLocationRelativeTo(null);
       lRFSBFrame.setResizable(false);
       lRFSBFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("Enter Book:");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		lRFSBFrame.add(lblNewLabel);
       JTextField textField = new JTextField();
       textField.setBounds(20, 50, 400, 35);
       textField.setForeground(Color.BLACK);
       textField.setBackground(Color.WHITE);
       textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       lRFSBFrame.add(textField);
       textField.addFocusListener((new FocusListener()
       {
           @Override
           public void focusLost(FocusEvent e)
           {
               if(textField.getText().isEmpty())
               {
               	textField.setText("Example: Shogun");
               }
           }
           @Override
           public void focusGained(FocusEvent e)
           {
               if(textField.getText().equals("Example: Shogun"))
               {
               	textField.setText("");
               }
           }
       }));
      
       JButton enterButton = new JButton("Enter");
       enterButton.setBounds(96, 90, 242, 51);
       enterButton.setBackground(Color.WHITE);
       enterButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       enterButton.setFocusPainted(false);
       lRFSBFrame.add(enterButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       lRFSBFrame.add(backButton);
      
       lRFSBFrame.setVisible(true);
      
       lRFSBFrame.addWindowListener(new java.awt.event.WindowAdapter()
       {
           public void windowOpened(java.awt.event.WindowEvent evt)
           {
               backButton.requestFocus();
           }
       });
      
       enterButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	String book = textField.getText();
           	try {
                   String sql = "SELECT ReviewText FROM REVIEW JOIN BOOK ON REVIEW.BookID = BOOK.BookID WHERE BOOK.Title = ?";
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setString(1, book);
                   ResultSet resultSet = statement.executeQuery();
                   StringBuilder reviews = new StringBuilder("Reviews for " + book + ":\n");
                   while (resultSet.next())
                   {
                       reviews.append(resultSet.getString("ReviewText")).append("\n");
                   }
                   if (reviews.length() == ("Reviews for " + book + ":\n").length())
                   {
                       JOptionPane.showMessageDialog(lRFSBFrame, "No reviews found for " + book);
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(lRFSBFrame, reviews.toString());
                   }
               }
           	catch (SQLException ex)
           	{
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(lRFSBFrame, "Error occurred while fetching reviews");
               }
           }
       });
      
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	lRFSBFrame.dispose();
           	queryingFunc();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	public void lBFSA()
	{
       JFrame lBFSAFrame = new JFrame("List Books From Specific Author");
       lBFSAFrame.setSize(450, 300);
       lBFSAFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       lBFSAFrame.setLocationRelativeTo(null);
       lBFSAFrame.setResizable(false);
       lBFSAFrame.setLayout(null);
       JLabel lblNewLabel = new JLabel("Enter Author Last Name:");
       lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		lBFSAFrame.add(lblNewLabel);
       JTextField textField = new JTextField();
       textField.setBounds(20, 50, 400, 35);
       textField.setForeground(Color.BLACK);
       textField.setBackground(Color.WHITE);
       textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       lBFSAFrame.add(textField);
       textField.addFocusListener((new FocusListener()
       {
           @Override
           public void focusLost(FocusEvent e)
           {
               if(textField.getText().isEmpty())
               {
               	textField.setText("Example: Brown");
               }
           }
           @Override
           public void focusGained(FocusEvent e)
           {
               if(textField.getText().equals("Example: Brown"))
               {
               	textField.setText("");
               }
           }
       }));
       JButton enterButton = new JButton("Enter");
       enterButton.setBounds(96, 90, 242, 51);
       enterButton.setBackground(Color.WHITE);
       enterButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       enterButton.setFocusPainted(false);
       lBFSAFrame.add(enterButton);
       JButton backButton = new JButton("Back");
       backButton.setBounds(96, 142, 242, 51);
       backButton.setBackground(Color.WHITE);
       backButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
       backButton.setFocusPainted(false);
       lBFSAFrame.add(backButton);
      
       lBFSAFrame.setVisible(true);
      
       lBFSAFrame.addWindowListener(new java.awt.event.WindowAdapter()
       {
           public void windowOpened(java.awt.event.WindowEvent evt)
           {
               backButton.requestFocus();
           }
       });
      
       enterButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	String lastName = textField.getText();
               try {
                   String sql = "SELECT * FROM BOOK JOIN AUTHOR ON BOOK.AuthorID = AUTHOR.AuthorID WHERE AUTHOR.LastName = ?";
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setString(1, lastName);
                   ResultSet resultSet = statement.executeQuery();
                   StringBuilder books = new StringBuilder("Books by " + lastName + ":\n");
                   while (resultSet.next()) {
                       books.append("Title: ").append(resultSet.getString("Title")).append("\n");
                       books.append("\n");
                   }
                   if (books.length() == ("Books by " + lastName + ":\n").length())
                   {
                       JOptionPane.showMessageDialog(lBFSAFrame, "No books found by " + lastName);
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(lBFSAFrame, books.toString());
                   }
               }
               catch (SQLException ex)
               {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(lBFSAFrame, "Error occurred while fetching books");
               }
           }
       });
      
       backButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
           	lBFSAFrame.dispose();
           	queryingFunc();
           }
       });
       addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	if (connection != null)
		    	{
		    		try
		    		{
						connection.close();
					}
		    		catch (SQLException e1)
		    		{
						e1.printStackTrace();
					}
	            }
		      e.getWindow().dispose();
		    }
		});
   }
	
	private void displayTable(Connection connection, String table)
	{
		try
		{
			Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);
	        StringBuilder tableContent = new StringBuilder("Table Contents:\n");
	        ResultSetMetaData metaData = resultSet.getMetaData();
           int columnCount = metaData.getColumnCount();
           while (resultSet.next()) 
           {
               for (int i = 1; i <= columnCount; i++) 
               {
                   tableContent.append(metaData.getColumnName(i)).append(": ").append(resultSet.getString(i)).append("\n");
               }
               tableContent.append("\n");
           }
           JOptionPane.showMessageDialog(this, tableContent.toString());
       } 
		catch (SQLException ex) 
		{
           ex.printStackTrace();
           JOptionPane.showMessageDialog(this, "Error occurred while fetching data.");
       }
   }
}

