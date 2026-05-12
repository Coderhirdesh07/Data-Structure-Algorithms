package DSA.desiqna.Trees;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Trees {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            list[i] = new ArrayList<Integer>();
        }
        int[] vis = new int[n+1];
        for(int i=0;i<n;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            list[x].add(y);
            list[y].add(x);
        }
        int[] color = new int[n+1];
        for(int i=1;i<=n;i++){
            color[i] = sc.nextInt();
        }
        question_1(list,vis);


    }
    public static  void  question_1(ArrayList<Integer>[] list,int[] vis){
        Queue<Integer> q1 = new LinkedList();
        q1.add(1);
        vis[1] = 1;
        while (!q1.isEmpty()){
            int node = q1.peek();
            q1.remove();
            int count = 0;
            for(int it:list[node]){
                if(vis[it]==0){
                    count++;
                    q1.add(it);
                    vis[it] = 1;
                }
            }
            list[node].add(count);
        }

    }
    // interview question of google
    public static  int[] question_2(ArrayList<Integer>[] list,int[] vis,int[] color){
        Queue<Integer> q1 = new LinkedList<>();
        int[] answer = new int[color.length];
        q1.add(1);
        vis[1] = 1;
        answer[1] = color[1];
        while (!q1.isEmpty()){
            int node = q1.peek();
            q1.remove();
            int count = 0;
            for(int x : list[node]){
                if(vis[x]==0){
                    count++;
                    vis[x]=1;
                    q1.add(x);
                    if(color[x]==1){
                        answer[x] = answer[node]+1;
                    }
                    else answer[x] = answer[node];
                }
            }
        }
        return answer;

    }

    // height of a tree
    public  static void question_3(int node,ArrayList<Integer>[] list,int[] vis,int[] parent,int[] height){
         vis[node] = 1;
         for(int x:list[node]){
             if(vis[x]==0){
                 parent[x] = node;
                  question_3(x,list,vis,parent,height);
             }
         }
         int h = 0;
         for(int it:list[node]){
             if(parent[node]==it){
                 continue;
             }
             else{
                 h = Math.max(height[it],h);
             }
         }
         height[node] = h;
    }
    // Google Interview Problem
    public  static void question_4(int node,ArrayList<Integer>[] list,int[] vis,int[] parent,int[] height){
        vis[node] = 1;
        for(int x:list[node]){
            if(vis[x]==0){
                parent[x] = node;
                question_3(x,list,vis,parent,height);
            }
        }
        int h = 0;
        for(int it:list[node]){
            if(parent[node]!=it){
                h = Math.max(height[it],h);
            }
        }
        height[node] = h+1;
    }

    public static  void oa_question(int node,ArrayList<Integer>[] list,int[] vis,int[] parent,int[] color,int[] dp,int[] b,int[] c){
            vis[node] = 1;
            for(int it:list[node]){
                if(vis[it]==0){
                    parent[it] = node;
                    oa_question(it,list,vis,parent,color,dp,b,c);
                }
            }
            int totalDistance = 0;
            int totalRedColor = 0;
            for(int x:list[node]){
                if(parent[node]==x){
                    continue;
                }
                else{
                   totalDistance=totalDistance+dp[x];
                   totalRedColor = totalRedColor + c[x];
                }
            }
           c[node] = c[node]+totalRedColor;
           dp[node] = totalDistance + totalRedColor;
           if(color[node]==1){
              c[node] = c[node] + 1;
           }
           else c[node]=c[node];
    }


}
