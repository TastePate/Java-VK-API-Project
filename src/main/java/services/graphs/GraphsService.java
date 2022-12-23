package services.graphs;

import models.Group;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import services.database.GroupService;

import javax.swing.*;
import java.util.List;

public class GraphsService extends ApplicationFrame {

    public GraphsService( String title ) {
        super( title );
        setContentPane(createDemoPanel( ));
    }

    private static PieDataset createDataset( ) {
        GroupService groupService = new GroupService();
        DefaultPieDataset dataset = new DefaultPieDataset( );

        groupService.findAll().forEach(group -> dataset.setValue(group.getName(), group.getMembersCount()));
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset ) {
        return ChartFactory.createPieChart(
                "My groups",   // chart title
                dataset,          // data
                true,             // include legend
                false,
                false);
    }

    public static JPanel createDemoPanel( ) {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

    public static void main( String[ ] args ) {
        GraphsService demo = new GraphsService( "Mobile Sales" );
        demo.setSize( 1920 , 1080 );
        RefineryUtilities.centerFrameOnScreen( demo );
        demo.setVisible( true );
    }
}

