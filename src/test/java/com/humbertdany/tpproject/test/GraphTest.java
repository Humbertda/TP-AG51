package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.chrono.Chrono;
import com.humbertdany.tpproject.util.generator.ArrayListIntegerGenerator;
import com.humbertdany.tpproject.util.graph.*;
import com.humbertdany.tpproject.util.graph.parser.KruskalAlgorithm;
import com.humbertdany.tpproject.util.graph.parser.PrimAlgorithm;

public class GraphTest extends ATest<Graph>{

	private final int numberOfTestToPerform;
	private final boolean shouldTestMSP;
	private final int maxVertexNumber;

	public GraphTest(int numberOfTestToPerform, boolean shouldTestMSP, int maxVertexNumber){
		this.numberOfTestToPerform = numberOfTestToPerform;
		this.shouldTestMSP = shouldTestMSP;
		this.maxVertexNumber = maxVertexNumber;
	}

	public static GraphTest withMsp(){
		return new GraphTest(1, true, 500);
	}

	public static GraphTest withoutMsp() {
		return new GraphTest(50, false, 10000);
	}

	@Override
	public void launch() {

		final Chrono chr = new Chrono();
		final ClassicResultEntry dsfResultEntry = new ClassicResultEntry("Graph DSF", "the dsf executed from root in average in");
		final ClassicResultEntry bdsResultEntry = new ClassicResultEntry("Graph BDS", "the bds executed from root in average in");
		final ClassicResultEntry mstKruskalResultEntry = new ClassicResultEntry("Kruskal MST", "the minimum spanning tree search has executed in");
		final ClassicResultEntry mstPrimResultEntry = new ClassicResultEntry("Prim MST", "the minimum spanning tree search has executed in");

		for(int numberOfVertex = 100; numberOfVertex <= maxVertexNumber ; numberOfVertex = new Double(numberOfVertex * 1.8).intValue()) {
			final int numberOfEdges = numberOfVertex * 12;
			final ArrayListIntegerGenerator costGenerator = new ArrayListIntegerGenerator(0, 1000);
			final ArrayListIntegerGenerator vertexIdGenerator = new ArrayListIntegerGenerator(0, numberOfVertex - 1);
			log("Running test for a graph dimension of " + numberOfVertex);

			for (int nbTest = 0; nbTest < numberOfTestToPerform; nbTest++) {

				final Graph<VData> graph = new Graph<>();

				// Adding 100 random vertex
				for (int i = 0; i < numberOfVertex; i++) {
					final VVertex vertex = new VVertex("Vertex ID " + i + " dim"+numberOfVertex, i, new VData());
					graph.addVertex(vertex);
					if (i == 0) {
						graph.setRootVertex(vertex);
					}
				}

				// Adding 2500 random edges connected to random Vertex
				for (int i = 0; i < numberOfEdges; i++) {
					graph.addEdge(graph.getVertex(vertexIdGenerator.buildObject()), graph.getVertex(vertexIdGenerator.buildObject()), new VEdgeData(costGenerator.buildObject()));
				}

				chr.start();
				graph.dfsSpanningTree(graph.getRootVertex(), new DFSVisitor<VData>() {
					@Override
					public void visit(Graph<VData> g, Vertex<VData> v) {
						execVertex(v);
					}

					@Override
					public void visit(Graph<VData> g, Vertex<VData> v, Edge<VData> e) {
						execVertex(v);
					}
				});
				chr.stop();
				dsfResultEntry.add(numberOfVertex, chr.getMilliSec());

				chr.start();
				graph.breadthFirstSearch(graph.getRootVertex(), (Visitor<VData>) (g, v) -> execVertex(v));
				chr.stop();
				bdsResultEntry.add(numberOfVertex, chr.getMilliSec());

				if(shouldTestMSP){
					chr.start();
					final KruskalAlgorithm<VData> vDataKruskalAlgorithm = new KruskalAlgorithm<>();
					vDataKruskalAlgorithm.getMinimumSpanningTreeAlgorithm(graph);
					chr.stop();
					mstKruskalResultEntry.add(numberOfVertex, chr.getMilliSec());

					chr.start();
					final PrimAlgorithm<VData> vDataPrimlAlgorithm = new PrimAlgorithm<>();
					vDataPrimlAlgorithm.getMinimumSpanningTreeAlgorithm(graph);
					chr.stop();
					mstPrimResultEntry.add(numberOfVertex, chr.getMilliSec());
				}

			}
		}

		if(shouldTestMSP){
			mstKruskalResultEntry.displayResults();
			mstPrimResultEntry.displayResults();
		} else {
			dsfResultEntry.displayResults();
			bdsResultEntry.displayResults();
		}

	}

	private void execVertex(Vertex<VData> v){
		v.getName();
	}

	private final class VVertex extends Vertex<VData>{

		VVertex(String n, Integer id, VData data){
			super(n, id, data);
		}

		@Override
		public double getCostTo(Vertex<VData> v){
			for(Edge e : this.getOutgoingEdges()){
				if(e.getTo().getName().equals(v.getName())){
					final VEdgeData data = (VEdgeData) e.getData();
					return data.cost;
				}
			}
			return -1;
		}
	}

	private final class VData extends VertexData {

	}

	private final class VEdgeData extends EdgeData {

		final Integer cost;

		VEdgeData(Integer cost){
			this.cost = cost;
		}

	}

}
