package com.celula.tmtool.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.celula.tmtool.analyser.Analyser;
import com.celula.tmtool.entity.Amplicon;
import com.celula.tmtool.entity.Hotspot;
import com.celula.tmtool.entity.Sample;
import com.celula.tmtool.reader.BamReader;
import com.celula.tmtool.reader.BamReader.CppReader.Amplicon_GC;
import com.celula.tmtool.util.RengineUtil;
import com.celula.tmtool.util.TableComparater;

/**
 * Example of components laid out in a grid
 */
public class TMTool extends javax.swing.JFrame {
	private javax.swing.JComboBox ivjJComboBox1 = null;
	private javax.swing.JPanel ivjJFrameContentPane = null;
	private javax.swing.JProgressBar ivjJProgressBar1 = null;
	private javax.swing.JRadioButton ivjJRadioButton1 = null;
	private javax.swing.JScrollBar ivjJScrollBar1 = null;
	private javax.swing.JSlider ivjJSlider1 = null;
	private javax.swing.JTextArea ivjJTextArea1 = null;
	private javax.swing.JToggleButton ivjJToggleButton1 = null;
	private JPanel jPanel1;
	private JPanel jPanel0;
	private JPanel jPanel2;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JButton jButton0;
	private JCheckBox jCheckBox0;
	private JPanel jPanel3;
	private JTable bamViewTable;
	private JScrollPane jScrollPane0;
	private JComboBox jComboBox0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JLabel jLabel3;
	private JButton plot_button;
	private JTextField jTextField2;
	private JCheckBox jCheckBox1;
	private JCheckBox jCheckBox2;
	private JPanel jPanel4;
	private JSeparator jSeparator0;
	private JButton fetal_frac_button;
	private JButton jButton4;
	private JCheckBox jCheckBox3;
	private JLabel jLabel4;
	private JTextField jTextField3;
	private JLabel jLabel5;
	private JTextField jTextField4;
	private JPanel jPanel5;
	private JLabel jLabel6;
	private JTextField jTextField5;
	private JLabel jLabel7;
	private JTextField jTextField6;
	private JPanel jPanel6;
	private JLabel jLabel8;
	private JTextField jTextField7;
	private JButton jButton5;
	private JLabel jLabel9;
	private JTextField jTextField8;
	private JButton jButton6;
	private JPanel jPanel7;
	private JPanel jPanel8;
	private JScrollPane jScrollPane1;
	private JPanel jPanel9;
	private JTable jTable1;
	private JPanel jPanel10;
	private JScrollPane jScrollPane2;
	private JTable jTable2;
	private JScrollPane jScrollPane3;
	private JTable jTable3;
	private JCheckBox export_combobox;
	private JLabel jLabel10;
	private JScrollBar jScrollBar0;
	private JButton java_plot_button;
	private JTable jTable4;
	private BamviewTableModel bamViewTableModel;
	private AmpliconBedTableModel ampliconBedTableModel;
	private HotspotBedTableModel hotspotBedTableModel;
	private Hashtable<String, Sample> sampleHash;
	private JLabel jLabel11;

	public TMTool() {
		super();
		initialize();
	}

	/**
	 * Return the JComboBox1 property value.
	 * @return javax.swing.JComboBox
	 */
	private javax.swing.JComboBox getJComboBox1() {
		if (ivjJComboBox1 == null) {
			ivjJComboBox1 = new javax.swing.JComboBox();
			ivjJComboBox1.setName("JComboBox1");
		}
		return ivjJComboBox1;
	}

	private JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {
			ivjJFrameContentPane = new JPanel();
			ivjJFrameContentPane.setLayout(new CardLayout());
			ivjJFrameContentPane.add(getJPanel0(), "jPanel0");
		}
		return ivjJFrameContentPane;
	}

	/**
	 * Return the JProgressBar1 property value.
	 * @return javax.swing.JProgressBar
	 */
	private javax.swing.JProgressBar getJProgressBar1() {
		if (ivjJProgressBar1 == null) {
			ivjJProgressBar1 = new javax.swing.JProgressBar();
			ivjJProgressBar1.setName("JProgressBar1");
			ivjJProgressBar1.setValue(50);
		}
		return ivjJProgressBar1;
	}

	/**
	 * Return the JRadioButton1 property value.
	 * @return javax.swing.JRadioButton
	 */
	private javax.swing.JRadioButton getJRadioButton1() {
		if (ivjJRadioButton1 == null) {
			ivjJRadioButton1 = new javax.swing.JRadioButton();
			ivjJRadioButton1.setName("JRadioButton1");
			ivjJRadioButton1.setText("JRadioButton");
		}
		return ivjJRadioButton1;
	}

	/**
	 * Return the JScrollBar1 property value.
	 * @return javax.swing.JScrollBar
	 */
	private javax.swing.JScrollBar getJScrollBar1() {
		if (ivjJScrollBar1 == null) {
			ivjJScrollBar1 = new javax.swing.JScrollBar();
			ivjJScrollBar1.setName("JScrollBar1");
		}
		return ivjJScrollBar1;
	}

	/**
	 * Return the JSlider1 property value.
	 * @return javax.swing.JSlider
	 */
	private javax.swing.JSlider getJSlider1() {
		if (ivjJSlider1 == null) {
			ivjJSlider1 = new javax.swing.JSlider();
			ivjJSlider1.setName("JSlider1");
		}
		return ivjJSlider1;
	}

	/**
	 * Return the JTextArea1 property value.
	 * @return javax.swing.JTextArea
	 */
	private javax.swing.JTextArea getJTextArea1() {
		if (ivjJTextArea1 == null) {
			ivjJTextArea1 = new javax.swing.JTextArea();
			ivjJTextArea1.setName("JTextArea1");
			ivjJTextArea1.setRows(3);
			ivjJTextArea1.setColumns(7);
		}
		return ivjJTextArea1;
	}

	/**
	 * Return the JToggleButton1 property value.
	 * @return javax.swing.JToggleButton
	 */
	private javax.swing.JToggleButton getJToggleButton1() {
		if (ivjJToggleButton1 == null) {
			ivjJToggleButton1 = new javax.swing.JToggleButton();
			ivjJToggleButton1.setName("JToggleButton1");
			ivjJToggleButton1.setText("JToggleButton");
		}
		return ivjJToggleButton1;
	}

	private void initialize() {
		setTitle("BasicSwingComponents");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(getJFrameContentPane());
		setSize(685, 685);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJPanel2(), new Constraints(new Leading(0, 373, 10, 10), new Leading(3, 74, 10, 10)));
			jPanel1.add(getJPanel3(), new Constraints(new Leading(0, 384, 12, 12), new Leading(83, 132, 12, 12)));
			jPanel1.add(getJPanel4(), new Constraints(new Leading(402, 267, 12, 12), new Leading(10, 192, 10, 10)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Bilateral(0, -8, 0), new Leading(38, 216, 10, 10)));
			jPanel0.add(getJSeparator0(), new Constraints(new Leading(4, 674, 10, 10), new Leading(260, 10, 12, 12)));
			jPanel0.add(getFetal_frac_button(), new Constraints(new Leading(39, 10, 10), new Leading(264, 10, 10)));
			jPanel0.add(getJButton4(), new Constraints(new Leading(142, 10, 10), new Leading(266, 12, 12)));
			jPanel0.add(getJCheckBox3(), new Constraints(new Leading(134, 10, 10), new Leading(304, 12, 12)));
			jPanel0.add(getJPanel5(), new Constraints(new Leading(284, 356, 10, 10), new Leading(266, 12, 12)));
			jPanel0.add(getJPanel7(), new Constraints(new Leading(15, 282, 12, 12), new Leading(344, 48, 10, 10)));
			jPanel0.add(getJPanel6(), new Constraints(new Leading(311, 308, 10, 10), new Leading(306, 30, 12, 12)));
			jPanel0.add(getJPanel8(), new Constraints(new Leading(315, 283, 12, 12), new Leading(348, 49, 10, 10)));
			jPanel0.add(getJPanel10(), new Constraints(new Leading(317, 319, 10, 10), new Leading(403, 10, 10)));
			jPanel0.add(getJPanel9(), new Constraints(new Leading(12, 285, 12, 12), new Leading(403, 174, 10, 10)));
		}
		return jPanel0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.add(getJLabel0());
			jPanel2.add(getJTextField0());
			jPanel2.add(getJButton0());
			jPanel2.add(getJCheckBox0());
		}
		return jPanel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Data Folder");
		}
		return jLabel0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setPreferredSize(new Dimension(80,25));;
			jTextField0.setText("");
		}
		return jTextField0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("...");
			jButton0.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton0ActionActionPerformed(event);
				}
			});
		}
		return jButton0;
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setText("Select All");
		}
		return jCheckBox0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane0(), new Constraints(new Leading(44, 317, 10, 10), new Leading(6, 117, 10, 10)));
		}
		return jPanel3;
	}

	private JTable getJTable0() {
		if (bamViewTable == null) {
			bamViewTable = new JTable();
			bamViewTableModel = new BamviewTableModel();
			bamViewTableModel.addColumn("checked");
			bamViewTableModel.addColumn("SampleID");
			bamViewTableModel.addColumn("Chip");
			bamViewTableModel.addColumn("Barcode");
			bamViewTableModel.addColumn("BAM files");
			bamViewTableModel.addColumn("BAI files");
			bamViewTable.setSize(300, 500);
			bamViewTable.setModel(bamViewTableModel);
		}
		return bamViewTable;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(new LineBorder(Color.black, 1, false));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			jComboBox0.setDoubleBuffered(false);
			jComboBox0.setBorder(null);
		}
		return jComboBox0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("select Chip:");
		}
		return jLabel1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Group Number:");
		}
		return jLabel2;
	}


	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setPreferredSize(new Dimension(40, 25));
			jTextField1.setText("");
		}
		return jTextField1;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Flank Flows:");
		}
		return jLabel3;
	}

	private JButton getJButton2() {
		if (plot_button == null) {
			plot_button = new JButton();
			plot_button.setText("jButton2");
		}
		return plot_button;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setPreferredSize(new Dimension(40, 25));
			jTextField2.setText("");
		}
		return jTextField2;
	}

	private JCheckBox getJCheckBox1() {
		if (jCheckBox1 == null) {
			jCheckBox1 = new JCheckBox();
			jCheckBox1.setText("Batch run");
		}
		return jCheckBox1;
	}

	private JCheckBox getJCheckBox2() {
		if (jCheckBox2 == null) {
			jCheckBox2 = new JCheckBox();
			jCheckBox2.setSelected(false);
			jCheckBox2.setText("Save Image");
		}
		return jCheckBox2;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJComboBox0(), new Constraints(new Leading(183, 10, 10), new Leading(8, 10, 10)));
			jPanel4.add(getJLabel2(), new Constraints(new Leading(16, 10, 10), new Leading(45, 10, 10)));
			jPanel4.add(getJLabel3(), new Constraints(new Leading(16, 12, 12), new Leading(75, 12, 12)));
			jPanel4.add(getJCheckBox1(), new Constraints(new Leading(143, 10, 10), new Leading(99, 12, 12)));
			jPanel4.add(getJCheckBox2(), new Constraints(new Leading(141, 10, 10), new Leading(129, 10, 10)));
			jPanel4.add(getJTextField1(), new Constraints(new Leading(106, 10, 10), new Leading(42, 12, 12)));
			jPanel4.add(getJTextField2(), new Constraints(new Leading(106, 12, 12), new Leading(69, 12, 12)));
			jPanel4.add(getJLabel10(), new Constraints(new Leading(21, 12, 12), new Leading(166, 12, 12)));
			jPanel4.add(getPlot_button(), new Constraints(new Leading(28, 12, 12), new Leading(100, 12, 12)));
			jPanel4.add(getJava_plot_button(), new Constraints(new Leading(177, 10, 10), new Leading(51, 10, 10)));
			jPanel4.add(getJScrollBar0(), new Constraints(new Leading(130, 106, 12, 12), new Leading(161, 12, 12)));
			jPanel4.add(getJLabel1(), new Constraints(new Leading(108, 10, 10), new Leading(12, 12, 12)));
			jPanel4.add(getExport_combobox(), new Constraints(new Leading(61, 12, 12), new Leading(134, 12, 12)));
			jPanel4.add(getJLabel11(), new Constraints(new Leading(7, 12, 12), new Leading(137, 12, 12)));
		}
		return jPanel4;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JButton getJButton3() {
		if (fetal_frac_button == null) {
			fetal_frac_button = new JButton();
			fetal_frac_button.setText("jButton3");
		}
		return fetal_frac_button;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Export Flow Matrix");
		}
		return jButton4;
	}

	private JCheckBox getJCheckBox3() {
		if (jCheckBox3 == null) {
			jCheckBox3 = new JCheckBox();
			jCheckBox3.setText("Batch");
		}
		return jCheckBox3;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Flow 1:");
		}
		return jLabel4;
	}
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setPreferredSize(new Dimension(80,25));
			jTextField3.setText("");
		}
		return jTextField3;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Flow 2:");
		}
		return jLabel5;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setPreferredSize(new Dimension(80,25));
			jTextField4.setText("");
		}
		return jTextField4;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.add(getJLabel4());
			jPanel5.add(getJTextField3());
			jPanel5.add(getJLabel5());
			jPanel5.add(getJTextField4());
		}
		return jPanel5;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Allele 1:");
		}
		return jLabel6;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setPreferredSize(new Dimension(80,25));
			jTextField5.setText("");
		}
		return jTextField5;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Allele 2:");
		}
		return jLabel7;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setPreferredSize(new Dimension(80, 25));
			jTextField6.setText("");
		}
		return jTextField6;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.add(getJLabel6());
			jPanel6.add(getJTextField5());
			jPanel6.add(getJLabel7());
			jPanel6.add(getJTextField6());
		}
		return jPanel6;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Amplicon BED file:");
		}
		return jLabel8;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setPreferredSize(new Dimension(80, 25));
			jTextField7.setText("");
		}
		return jTextField7;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("...");
			jButton5.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton5ActionActionPerformed(event);
				}
			});
		}
		return jButton5;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Hotspot BED file:");
		}
		return jLabel9;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setPreferredSize(new Dimension(80,25));
			jTextField8.setText("");
		}
		return jTextField8;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("...");
			jButton6.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton6ActionActionPerformed(event);
				}
			});
		}
		return jButton6;
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.add(getJLabel8());
			jPanel7.add(getJTextField7());
			jPanel7.add(getJButton5());
		}
		return jPanel7;
	}

	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jPanel8 = new JPanel();
			jPanel8.add(getJLabel9());
			jPanel8.add(getJTextField8());
			jPanel8.add(getJButton6());
		}
		return jPanel8;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBorder(new LineBorder(Color.black, 1, false));
			jScrollPane1.setViewportView(getJTable4());
		}
		return jScrollPane1;
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJScrollPane1(), new Constraints(new Leading(7, 263, 10, 10), new Leading(12, 146, 10, 10)));
		}
		return jPanel9;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setBorder(new LineBorder(Color.black, 1, false));
			jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable1;
	}

	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			jPanel10 = new JPanel();
			jPanel10.setLayout(new GroupLayout());
			jPanel10.add(getJScrollPane3(), new Constraints(new Leading(25, 270, 10, 10), new Leading(9, 150, 10, 10)));
		}
		return jPanel10;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
		}
		return jScrollPane2;
	}

	private JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
			jTable2.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable2;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBorder(new LineBorder(Color.black, 1, false));
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	private JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable();
			hotspotBedTableModel = new HotspotBedTableModel();
			hotspotBedTableModel.addColumn("select");
			hotspotBedTableModel.addColumn("Chromosome");
			hotspotBedTableModel.addColumn("Position");
			hotspotBedTableModel.addColumn("Ref");
			hotspotBedTableModel.addColumn("Alt");
			hotspotBedTableModel.addColumn("Index");
			
			jTable3.setModel(hotspotBedTableModel);
		}
		return jTable3;
	}

	private JButton getFetal_frac_button() {
		if (fetal_frac_button == null) {
			fetal_frac_button = new JButton();
			fetal_frac_button.setText("Fetal Frac.");
		}
		return fetal_frac_button;
	}

	private JButton getPlot_button() {
		if (plot_button == null) {
			plot_button = new JButton();
			plot_button.setText("plot");
			plot_button.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					plot_buttonActionActionPerformed(event);
				}
			});
		}
		return plot_button;
	}

	private JCheckBox getJCheckBox4() {
		if (export_combobox == null) {
			export_combobox = new JCheckBox();
			export_combobox.setText("export");
		}
		return export_combobox;
	}

	private JCheckBox getExport_combobox() {
		if (export_combobox == null) {
			export_combobox = new JCheckBox();
			export_combobox.setText("export");
		}
		return export_combobox;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Start Flow:");
		}
		return jLabel10;
	}

	private JScrollBar getJScrollBar0() {
		if (jScrollBar0 == null) {
			jScrollBar0 = new JScrollBar();
			jScrollBar0.setOrientation(JScrollBar.HORIZONTAL);
		}
		return jScrollBar0;
	}

	private JButton getJButton1() {
		if (java_plot_button == null) {
			java_plot_button = new JButton();
			java_plot_button.setText("jButton1");
		}
		return java_plot_button;
	}

	private JButton getJava_plot_button() {
		if (java_plot_button == null) {
			java_plot_button = new JButton();
			java_plot_button.setText("java_plot");
			java_plot_button.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					java_plot_buttonMouseMouseClicked(event);
				}
			});
		}
		return java_plot_button;
	}

	private JTable getJTable4() {
		if (jTable4 == null) {
			jTable4 = new JTable();
			ampliconBedTableModel = new AmpliconBedTableModel();
			ampliconBedTableModel.addColumn("checked");
			ampliconBedTableModel.addColumn("Chromosome");
			ampliconBedTableModel.addColumn("Start");
			ampliconBedTableModel.addColumn("End");
			ampliconBedTableModel.addColumn("Sequence");
			ampliconBedTableModel.addColumn("Index");
			ampliconBedTableModel.addColumn("Strand");
			jTable4.setModel(ampliconBedTableModel);
			TableRowSorter<AmpliconBedTableModel> tableRowSorter = new TableRowSorter<AmpliconBedTableModel>(ampliconBedTableModel);
			tableRowSorter.setSortable(0, false);
			for(int i = 1 ; i <=6 ; i ++){
				tableRowSorter.setComparator(i, new TableComparater());
			}
			jTable4.setRowSorter(tableRowSorter);
		}
		return jTable4;
	}

	private void java_plot_buttonMouseMouseClicked(MouseEvent event) {
		
	}

	
	private void jButton0ActionActionPerformed(ActionEvent event) {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setDialogTitle("Choose a folder");
		jFileChooser.setSelectedFile(new File("Y:\\Torrent_Server_001\\sequencing\\."));
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int showOpenDialog = jFileChooser.showOpenDialog(TMTool.this);
		String selectedPath="Y:\\Torrent_Server_001\\sequencing";
		if(showOpenDialog != JFileChooser.APPROVE_OPTION){
			return ; 
		}
		selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
		String path = selectedPath;
		if(path.substring(path.length() - 1).equals("\\")){
			path+="\\";
		}
		jTextField0.setText(path);
		File dirPath = new File(path);
		File[] listFolders = dirPath.listFiles(new FolderFilter());
		String[] chips = new String[listFolders.length];
		jComboBox0.removeAllItems();
		
		
		sampleHash = new Hashtable<String,Sample>();
			for(int i = 0 ; i < listFolders.length ; i++){
				String dirs_i = listFolders[i].toString();
				int idx = dirs_i.lastIndexOf('\\');
				if (idx != -1){
					chips[i] = dirs_i.substring(idx + 1);
				}
				String index_file = dirs_i + "\\index.txt";
				BufferedReader br = null;
				File idxFile =null;
				try {
					idxFile = new File(index_file);
					br = new BufferedReader(new FileReader(idxFile));
					while(true){
						String line = br.readLine();
	                    if (line == null)
	                        break;
	                    String[] split = line.split("\t");
	                    if (split.length != 6)
	                        continue;
	                    String barcodeSeq = split[2];
	                    String adapterSeq = split[3];
	                    String sampleID = split[4];
	                    String barcode = split[1];
	                    String chipID = split[0];
	                    String file = listFolders[i] + "\\" + split[5];
	                    if (!sampleHash.containsKey(chipID + "." + barcode))
	                    {
	                    	Sample s = new Sample();
	                        s.BAIfile = null;
	                        s.BAMfile = null;
	                        s.chipID = chipID;
	                        s.barcode = barcode;
	                        s.barcodeSeq = barcodeSeq;
	                        s.sampleID = sampleID;
	                        s.adapterSeq = adapterSeq;
	                        if (file.substring(file.length() - 3).equals("bam"))
	                            s.BAMfile = file;
	                        else if (file.substring(file.length() - 3).equals("bai"))
	                            s.BAIfile = file;
	                        sampleHash.put(chipID + "." + barcode, s);
	                    }
	                    else
	                    {
	                    	Sample s = (Sample)sampleHash.get(chipID + "." + barcode);
	                        if (file.substring(file.length() - 3).equals("bam"))
	                            s.BAMfile = file;
	                        else if (file.substring(file.length() - 3).equals("bai"))
	                            s.BAIfile = file;
	                        sampleHash.put(chipID + "." + barcode, s);
	                    }
					}
				
				} catch (Exception e) {
					continue;
				} finally {
					if(br!=null){
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
			bamViewTableModel.setNumRows(0);
			Set<Entry<String, Sample>> entrySet = sampleHash.entrySet();
			Iterator<Entry<String, Sample>> iterator = entrySet.iterator();
			while(iterator.hasNext()){
				Entry<String, Sample> next = iterator.next();
				Sample s = next.getValue();
				Object[] object = new Object[]{false,s.sampleID,s.chipID,s.barcode,s.BAMfile,s.BAIfile};
				bamViewTableModel.addRow(object);
				
			}
		
	}
	class FolderFilter implements FileFilter{
		@Override
		public boolean accept(File pathname) {
			return pathname.isDirectory();
		}
	}

	public class BamviewTableModel extends DefaultTableModel{

		private static final long serialVersionUID = 1L;
		Class<?>[] types = new Class<?>[] { Boolean.class,String.class, String.class, String.class,String.class, String.class };

		public Class<?> getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
	
	}
	
	public class AmpliconBedTableModel extends DefaultTableModel{

		private static final long serialVersionUID = 1L;
		Class<?>[] types = new Class<?>[] { Boolean.class,String.class, String.class, String.class,String.class, String.class, String.class };

		public Class<?> getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
	
	}
	public class HotspotBedTableModel extends DefaultTableModel{

		private static final long serialVersionUID = 1L;
		Class<?>[] types = new Class<?>[] { Boolean.class,String.class, String.class, String.class,String.class, String.class};

		public Class<?> getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
	
	}
	
	private void jButton5ActionActionPerformed(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File("F:\\2.工作\\02.java数据可视化\\Data\\wud源码\\感兴趣的基因组区域\\250plex_v0-6-2_20140909.bed"));
        int showOpenDialog = jFileChooser.showOpenDialog(TMTool.this);
        if(showOpenDialog != JFileChooser.APPROVE_OPTION){
			return ; 
        }
        String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
        Analyser.getInstance().loadAmplicons(selectedPath);
        jTextField7.setText(selectedPath);
        ampliconBedTableModel.setRowCount(0);
        int AmplicationSize = Analyser.getInstance().getAmplicons().size();
        Hashtable<String, Amplicon> amplicons = Analyser.getInstance().getAmplicons();
        for (int index = 0; index < AmplicationSize; index++)
        {
            Amplicon amplicon = amplicons.get(Integer.toString(index));
            Object[] rowData = new Object[]{false,amplicon.chromosome,amplicon.start,amplicon.end,amplicon.sequence,index,amplicon.strand};
            ampliconBedTableModel.addRow(rowData);
        }
	}

	private void jButton6ActionActionPerformed(ActionEvent event) {
		JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File("F:\\2.工作\\02.java数据可视化\\Data\\wud源码\\感兴趣的基因组区域\\250plex_v0-6-2_HotSpots_20140909.bed"));
        int showOpenDialog = jFileChooser.showOpenDialog(TMTool.this);
        if(showOpenDialog != JFileChooser.APPROVE_OPTION){
			return ; 
        }
        String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
        Analyser.getInstance().loadHotSpot(selectedPath);
        jTextField8.setText(selectedPath);
        hotspotBedTableModel.setRowCount(0);
        int hotspotSize = Analyser.getInstance().getHotspots().size();
        Hashtable<String, Hotspot> hotspots = Analyser.getInstance().getHotspots();
        for (int i = 0; i < hotspotSize; i++)
        {
            Hotspot hotspot = (Hotspot)hotspots.get(Integer.toString(i));
            Object[] rowData = new Object[]{false,hotspot.chromosome,hotspot.position,hotspot.refAllele,hotspot.altAllele,i};
            hotspotBedTableModel.addRow(rowData);
        }
	}

	private void plot_buttonActionActionPerformed(ActionEvent event) {
		String chipID = null, barcode = null, bamfile = null, barcodeSeq = null, adapterSeq = null;
		int rowCount = ampliconBedTableModel.getRowCount();
		for(int i = 0 ; i < rowCount ; i ++){
			Object valueAt = ampliconBedTableModel.getValueAt(i, 0);
			if((Boolean)ampliconBedTableModel.getValueAt(i, 0)==true){
				Amplicon amplicon = Amplicon.ListViewItem2Amplicon(ampliconBedTableModel, i);
				if (amplicon.chromosome == null || amplicon.start == -1 || amplicon.end == -1)
                    continue;
                if (amplicon.start > amplicon.end)
                {
                    int t = amplicon.end;
                    amplicon.end = amplicon.start;
                    amplicon.start = t;
                }
                
                Hotspot[] hotspots = Analyser.getInstance().findMatchedHotspot(amplicon);
                for(int j=0; j <hotspots.length;j++){
                	int rowCount2 = bamViewTableModel.getRowCount();
                	for (int k = 0; k < rowCount2; k++) {
						if((boolean)bamViewTableModel.getValueAt(k, 0)==true){
							chipID = (String)bamViewTableModel.getValueAt(k, 2);
	                        barcode = (String)bamViewTableModel.getValueAt(k, 3);
	                        Sample s = (Sample)sampleHash.get(chipID + "." + barcode);
	                        bamfile = s.BAMfile;
	                        barcodeSeq = s.barcodeSeq;
	                        adapterSeq = s.adapterSeq;
	 
	                        if (chipID == null || barcode == null || bamfile == null)
	                            continue;

	                        if (barcodeSeq == null || adapterSeq == null)
	                            continue;
							BamReader bamReader = new BamReader();
							Amplicon_GC readsInAmplicon = bamReader.readsInAmplicon(bamfile, amplicon.chromosome, amplicon.start, amplicon.end, 275, 30000, 300, hotspots[j].position);
//	                        if(export_combobox.isSelected()){
//	                        	 int rowCount3 = readsInAmplicon.flowValue.length;
//                                 int colCount = readsInAmplicon.flowValue[0].length;
//                                 
//                                 byte[] fv = new byte[rowCount * colCount * sizeof(int)];
//                                 Stream strm = new MemoryStream(fv);
//                                 StreamWriter sw = new StreamWriter(strm);
//                                 
//                                 for (int i_Bam = 0; i_Bam < rowCount; i_Bam++)
//                                 {
//                                     for (int j_Bam = 0; j_Bam < colCount; j_Bam++)
//                                         sw.Write(BAM.currentAMP.flowValue[i_Bam, j_Bam].ToString() + "\t");
//                                     sw.Write(BAM.currentAMP.hotspotFlow[i_Bam].ToString() + "\t");//
//                                     sw.Write(BAM.currentAMP.hotspotBase[i_Bam].ToString() + "\t");//
//                                     sw.Write(BAM.currentAMP.startFlow[i_Bam].ToString() + "\t");//
//                                     sw.Write(BAM.currentAMP.startPos[i_Bam].ToString() + "\t");//
//                                     sw.Write(new string(BAM.currentAMP.seq[i_Bam]) + "\t");//
//                                     sw.Write(new string(BAM.currentAMP.cigar[i_Bam]));//
//                                     sw.Write("\n");
//                                 }
//                                 sw.Flush();
//                                 sw.Close();
//                                 sw.Dispose();
//                                 strm.Close();
//                                     string fv_s = System.Text.Encoding.Default.GetString(fv);
//                                     Clipboard.SetText(fv_s);                                        
//	                        }
							
							jLabel11.setText("ploting " + ampliconBedTableModel.getValueAt(i+1, 1) + "...");
							int[] allele_pos = Analyser.getInstance().expectedHotpotFlowIndex(amplicon,hotspots[j],readsInAmplicon,barcodeSeq,adapterSeq);
							jTextField3.setText(Integer.toString(Math.min(allele_pos[0], allele_pos[1])));
							jTextField4.setText(Integer.toString(Math.max(allele_pos[0], allele_pos[1])));
// TODO							
//							if (ANL.removeTooShortReads(max(allele_pos) + 20) < 100)
//	                                continue;

							int[] clusters = Analyser.getInstance().correctPolyclonalSignal(readsInAmplicon,allele_pos, 80);
							int[] alleleCount = Analyser.getInstance().getCorrectedAlleleCount(clusters);
							String t = alleleCount[0] + "\t" + alleleCount[1] + "\n";
							
							jTextField5.setText(alleleCount[0]+"");
							jTextField6.setText(alleleCount[1]+"");
							
							Analyser.getInstance().plotHeatmap(readsInAmplicon,25, Math.min(allele_pos[0], allele_pos[1]) - 10, true,clusters,allele_pos);
							//TODO
						}
						
						
					}
                }
                
			}
		}
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("jLabel11");
		}
		return jLabel11;
	}
}
