import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Textbereich{
    JButton pbClickMe;
    JCheckBox cbChangeText;
    JList<String> listGreek;
    JTextArea text;
    String areaText = "you just clicked me!\n";
    final String [] stringsGreek = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta"};

    public static void main(String[] args) {
        Textbereich app = new Textbereich();
        app.show();
    }

    public void show(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        pbClickMe = new JButton("just click me");
        pbClickMe.addActionListener(new textAreaPerformer());

        text = new JTextArea(10,20);
        text.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        cbChangeText = new JCheckBox("change the text!");
        cbChangeText.addItemListener(new itemCbChecker());

        listGreek = new JList<>(stringsGreek);
        listGreek.setVisibleRowCount(4);
        listGreek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listGreek.addListSelectionListener(new listGreekPerformer());
        JScrollPane greekScroller = new JScrollPane(listGreek);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(BorderLayout.EAST, greekScroller);
        panel.add(scroller);
        frame.add(BorderLayout.NORTH, cbChangeText);
        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, pbClickMe);
        frame.setSize(350,300);
        frame.setVisible(true);
    }
    class listGreekPerformer implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent var1){
            areaText = listGreek.getSelectedValue() + "\n";
            cbChangeText.setSelected(false);
            pbClickMe.setText("New Copy-Text: " + areaText);
        }
    }
    class textAreaPerformer implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            text.append(areaText);
        }
    }
    class itemCbChecker implements ItemListener{
        public void itemStateChanged(ItemEvent ev){
            areaText = cbChangeText.isSelected() ? "you changed my Text!\n":"you just clicked me!\n";
            pbClickMe.setText(cbChangeText.isSelected() ? "change the text!" : "just do it again!");
        }
    }

}