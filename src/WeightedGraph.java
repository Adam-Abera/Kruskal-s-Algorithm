import java.util.ArrayList;
import java.util.Comparator;

public class WeightedGraph {
    class Edge
    {
        public double weight;
        public int source;
        public int destination;
        public Edge(int Source, int Destination, double Weight)
        {
            this.source = Source;
            this.destination = Destination;
            this.weight = Weight;
        }
    }
    public static Comparator<Edge> edgeComparator = new Comparator<Edge>()
    {
        @Override
        public int compare(Edge e1, Edge e2)
        {
            return (int) Double.compare(e1.weight, e2.weight);
        }
    };
    int[] vertices;
    ArrayList<Edge> edges;
    public WeightedGraph(int n)
    {
        this.vertices = new int[n];
        for(int i = 0; i<n; i++)
        {
            vertices[i] = i;
            edges = new ArrayList<>();
        }
    }
    public WeightedGraph()
    {
        this.vertices = new int[10];
        for(int i = 0; i<10; i++)
        {
            vertices[i] = i;
            edges = new ArrayList<>();
        }
    }
    public int degree(int n)
    {
        int degree = 0;
        for(int i = 0; i < edges.size(); i++)
        {
            if(n == edges.get(i).source || n == edges.get(i).destination)
            {
                degree++;
            }
        }
        return degree;
    }
    public int edgeCount()
    {
        return edges.size();
    }
    public boolean insertEdge(int source, int destination, double weight)
    {

        int a = edgeExists(source, destination);
        System.out.println("a = " + a);
            if ((source > -1 && source < vertices.length) &&
                    (destination > -1 && destination < vertices.length)) {
                if (a == -1) {
                    edges.add(new Edge(source, destination, weight));
                } else if (a > -1 && edges.get(a).weight != weight) {
                    edges.get(a).weight = weight;
                }
                return true;
            } else {
                return false;
            }
    }
    public void eraseEdge(int Source, int Destination)
    {
        for(int i = 0; i<edges.size(); i++)
        {
            if(edges.get(i).source == Source && edges.get(i).destination == Destination)
            {
                edges.remove(i);
            }
        }
    }
    public void clearEdges(int Source, int Destination)
    {
        edges.clear();
    }
    public int edgeExists(int source, int destination)
    {
        if(edges != null)
        {
            for (int i = 0; i < edges.size(); i++)
            {
                if (edges.get(i).source == source && edges.get(i).destination == destination)
                {
                    return i;
                }
            }
            return -1;
        }
        return -2;
    }
    public double[] minimumSpanningTree()
    {
        double[] pair = new double[2];
        if(edges != null)
        {
            ArrayList<Edge> sortedEdges = edges;
            sortedEdges.sort(edgeComparator);
            ArrayList<Edge> minimumEdges = new ArrayList<Edge>();
            UnionSet set = new UnionSet(vertices.length);
            int n = 0;
            double weight = 0;
            for (int i = 0; i < edges.size(); i++) {
                if (n < vertices.length) {
                    if (!set.isCyclic(sortedEdges.get(i).source, sortedEdges.get(i).destination)) {
                        minimumEdges.add(sortedEdges.get(i));
                        weight += sortedEdges.get(i).weight;
                    }
                }
                else
                {
                    break;
                }
            }
            pair[0] = weight;
            pair[1] = minimumEdges.size();
            return pair;
        }
        pair[0] = -1;
        pair[1] = -1;
        return pair;
    }
    class UnionSet
    {
        int[] parent = new int[vertices.length];
        int[] vertex = new int[vertices.length];
        UnionSet(int n)
        {
            parent = new int[vertices.length];
            vertex = new int[vertices.length];
            for(int i = 0; i < n; i++)
            {
                vertex[i] = i;
                parent[i] = -1;
            }
        }
        int find (int v)
        {
            return parent[v];
        }
        void union (int v, int u)
        {
            if(parent[u] == -1 && parent[v] == -1)
            {
                if(u>v)
                {
                    parent[u] = u;
                    parent[v] = u;
                }
                if(u<v)
                {
                    parent[u] = v;
                    parent[v] = v;
                }
            }
            else
            {
                if (parent[u] > parent[v])
                {
                    for (int i = 0; i < vertices.length; i++)
                    {
                        if (parent[i] == parent[v]) {
                            parent[i] = parent[u];
                        }
                    }
                } else if (parent[v] > parent[u])
                {
                    parent[u] = parent[v];
                    for (int i = 0; i < vertices.length; i++)
                    {
                        if (parent[i] == parent[u]) {
                            parent[i] = parent[v];
                        }
                    }
                }
                else
                {

                }
            }
            //System.out.println(u + ", " + v + "=>"+ parent[u] + ", " + parent[v] );
        }
        boolean isCyclic(int v, int u)
        {
            if(find(v) == find(u) && (find(v) != -1 && find(u) != -1))
            {
                return true;
            }
            else
            {
                union(v,u);
                return false;
            }
        }
    }
}