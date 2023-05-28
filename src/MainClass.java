public class MainClass {
    public static void main(String[] args)
    {
        WeightedGraph graph = new WeightedGraph(4);
        graph.insertEdge( 0, 1, 7.2 );
        graph.insertEdge( 0, 2, 7.5 );
        graph.insertEdge( 0, 3, 7.3 );
        graph.insertEdge( 1, 2, 7.6 );
        graph.insertEdge( 1, 3, 7.4 );
        graph.insertEdge( 2, 3, 7.7 );
        System.out.println("Count: "+graph.edgeCount());
        for(int i=0;i<graph.edgeCount();i++)
        {
            System.out.println("Edge"+ i + ": (" + graph.edges.get(i).source + ", "+graph.edges.get(i).destination
                    + ", " + graph.edges.get(i).weight+")");
        }
        System.out.println("Weight: " + graph.minimumSpanningTree()[0]);
        // returns the minimum spanning tree weight: 22
        System.out.println("No of edges tested: " + (int)graph.minimumSpanningTree()[1]);
        // returns the number of nodes inspected: 4
        graph.insertEdge( 0, 2, 7.5 ); // returns true
        graph.insertEdge( 0, 2, 7.8 ); // returns true
        for(int i=0;i<graph.edgeCount();i++)
        {
            System.out.println("Edge"+ i + ": (" + graph.edges.get(i).source + ", "+graph.edges.get(i).destination
                    + ", " + graph.edges.get(i).weight+")");
        }
        System.out.println("Weight: " + graph.minimumSpanningTree()[0]);
        // returns the minimum spanning tree weight: 22.1
        System.out.println("No of edges tested: " + (int) graph.minimumSpanningTree()[1]);
        // returns the number of nodes inspected: 4

    }
}
