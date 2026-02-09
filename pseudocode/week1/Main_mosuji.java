import java.util.*;

class State{
    int y, x, h, w;
    State(int y, int x, int h, int w){
        this.y = y;
        this.x = x;
        this.h = h;
        this.w = w;
    }
}

public class Main {
    static int N, M;
    static State[] cur = new State[101];
    static int[][] info;
    static List<Integer> ans = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Please write your code here.
        N = sc.nextInt(); M = sc.nextInt();
        info = new int[N+1][N+1];
        
        for(int i=0; i<M; i++){
            int k = sc.nextInt();
            int h = sc.nextInt();
            int w = sc.nextInt();
            int x = sc.nextInt(); // 좌측 좌표
            int y = h;  // 맨 처음 및바닥 좌표는 h라고 가정 
            
            cur[k] = new State(y, x, h, w);
            gravity(k);
        }
        
        int cnt = M;
        while(cnt>0) {
        	leftMove();
        	cnt--;
        	if(cnt == 0) break;
        	
        	rightMove();
        	cnt--;
        }
        
        for(Integer i : ans) {
        	System.out.println(i);
        }
    }
    
    public static void rightMove() {
    	// 제거할 상자의 번호를 우선 구한다.
    	int num = 1000;
    	for(int i=1; i<=N; i++) {
    		for(int j=N; j>=1; j--) {
    			if(info[i][j] != 0 && checkMove(info[i][j], 1)) {
    				num = Math.min(num, info[i][j]);
    				break; // 한 행에서 만났다면 다음 행으로 넘어가기
    			}
    		}
    	}
    	
    	// 해당 상자 위에 있는 상자들을 순서대로 list에 저장한다.(먼저 만나는 순서)
    	List<Integer> upper = findUpper(num);
    	
    	
    	// 제거할 상자를 제거한다. 
    	boxRemove(num);
    	ans.add(num);
    	
    	// 제거한 상자 위에 있는 상자들에 중력을 적용한다.
    	for(Integer i : upper) {
    		gravity(i);
    	}	
    }
    
    public static void leftMove() {
    	
    	// 제거할 상자의 번호를 우선 구한다.
    	int num = 1000;
    	for(int i=1; i<=N; i++) {
    		for(int j=0; j<=N; j++) {
    			if(info[i][j] != 0 && checkMove(info[i][j], 0)) {
    				
    				num = Math.min(num, info[i][j]);
    				break; // 한 행에서 만났다면 다음 행으로 넘어가기
    			}
    		}
    	}
    	
    	// 해당 상자 위에 있는 상자들을 순서대로 list에 저장한다.(먼저 만나는 순서)
    	List<Integer> upper = findUpper(num);

    	
    	// 제거할 상자를 제거한다. 
    	boxRemove(num);
    	ans.add(num);
    	
    	// 제거한 상자 위에 있는 상자들에 중력을 적용한다.
    	for(Integer i : upper) gravity(i);
    }
    
    public static boolean checkMove(int num, int d) {
    	
    	boolean ans = true;
    	State box = cur[num];
    	// d = 0 이면 왼쪽 하차 검사
    	// num 상작 기준 왼쪽에 아무것도 없는지를 검사 
    	if(d == 0) {
    		for(int x = box.x-1; x>=1; x--) {
    			for(int y=box.y; y>box.y - box.h; y--) {
    				if(info[y][x] != 0) {
    					ans = false;
    					return ans;
    				}
    			}
    		}
    	}
    	
    	else {
    		for(int x = box.x+box.w; x<=N; x++) {
    			for(int y=box.y; y>box.y - box.h; y--) {
    				if(info[y][x] != 0) {
    					ans = false;
    					return ans;
    				}
    			}
    		}
    	}
    	return ans;
    }
    
    public static void boxRemove(int num) {
    	State box = cur[num];
    	for(int i= box.y; i>box.y-box.h; i--) {
    		for(int j=box.x; j<box.x+box.w; j++) {
    			if(info[i][j] == num) info[i][j] = 0;
    		}
    	}
    }
        
    // num 상자 위에 있는 상자를 구한다.
    public static List<Integer> findUpper(int num) {
    	Set<Integer> upperSet = new LinkedHashSet<>();  // 순서를 보장하는 set 
    	
    	List<Integer> targets = new ArrayList<>();
        targets.add(num);
        for (int k = 0; k < targets.size(); k++) {
            State box = cur[targets.get(k)];
            
            // 현재 조사 중인 상자의 가로 범위(x ~ x+w)를 한 열씩 검사
            for (int j = box.x; j < box.x + box.w; j++) {
                // 해당 열(j)에서 상자 바로 윗줄부터 천장(1)까지 위로 올라가며 탐색
                for (int i = box.y - box.h; i >= 1; i--) {
                    if (info[i][j] != 0) {
                        int found = info[i][j];
                        // 자기 자신이 아니고, 아직 찾지 못한 상자라면 추가
                        if (found != targets.get(k) && !upperSet.contains(found)) {
                            upperSet.add(found);
                            targets.add(found);
                        }
                        // 중요: 해당 열(j)에서는 가장 먼저 만난 상자 하나면 충분하므로 break
                        break; 
                    }
                }
            }
        }
    	

    	List<Integer> listCur = new ArrayList<>(upperSet); // set -> list 
    	Collections.sort(listCur, (a,b)-> {
    		State s1 = cur[(int)a], s2 = cur[(int)b];
    		return s2.y - s1.y;
    	});
    	return listCur;
    }

    // num 번 상자에 중력 적용 
    public static void gravity(int num){
    	
    	State box = cur[num];
    	int i = box.y+1;
    	boolean canMove = true;
    	for(; i<=N; i++) {
    		for(int j = box.x; j<box.x+box.w; j++) {
    			if(info[i][j] != 0) {
    				canMove = false;
    				break;
    			}
    			
    		}
    		if(canMove == false) break;
    	}
    	
    	if(canMove == false || i > N) i--; // 한 칸은 줄여야 함.(실패한 경우 대비해서)
    	
    	// 상자를 위치 정보를 업데이트하기 : 기존 위치를 모두 0으로 바꾸기
    	for(int y = box.y; y>box.y-box.h; y-- ) {
    		for(int x = box.x; x<box.x+box.w; x++) {
    			if(y >=1 && info[y][x] == num) info[y][x] = 0;
    		}
    	}
    	
    	// 새로운 위치에 박스 넣기
    	for(int y= i; y>=(i-box.h)+1; y--) {
    		for(int x = box.x; x<=box.x+ box.w-1; x++) {
    			if (y >= 1) info[y][x] = num;
    		}
    	}
    	
    	// 새로운 위치 정보 업데이트
    	cur[num] = new State(i, box.x, box.h, box.w);
    }

    public static void printArr() {
    	for(int i=1; i<=N; i++) {
    		for(int j=1; j<=N; j++) {
    			System.out.print(info[i][j]+" ");
    		}System.out.println();
    	}System.out.println();
    }
}
