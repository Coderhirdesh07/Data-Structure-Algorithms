package dsacourse851;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UnderstandingGraph {
    public static void main(String[] args) {

    }
    public static void bfs(int start, ArrayList<ArrayList<Integer>> list,int n){
        Queue<Integer> q1 = new LinkedList();
        boolean[] vis = new boolean[n];
        q1.add(start);

        vis[start] = true;
        while(!q1.isEmpty()){
            int node = q1.peek();
            q1.remove();

            for(int it:list.get(node)){
                if(!vis[it]){
                    q1.add(it);
                    vis[it] = true;
                }
            }
        }

    }
}
