#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void PrintCard(int* p, int length);
void PrintAllMemberCard(int* A, int* B, int* C, int* D, int* cardNum);
void CreateCardStack(int* cardStack);
void CardDistribute(int* A, int* B, int* C, int* D, int* cardStack, int* cardNum);
void ArrayPop(int* arr, int* arrLength, int poplndex);
void ArrayPush(int* arr, int* arrLength, int num);
void RemovePairedCard(int* holdingCard, int* cardNum);
void Game(int* A, int* B, int* C, int* D, int* cardNum, int* playerOrder);
void PrintPlayerOrder(int* playerOrder);


int main(void){
    int cardStack[53] = { 0 };
    int A[14] = { 0 }, B[14] = { 0 }, C[14] = { 0 }, D[14] = { 0 };
    int cardHoldingNum[4] = { 13, 13, 13, 14 };
    int playerOrder[4];
       

    CreateCardStack(cardStack);
    
    printf(">>>>> Card Distribution <<<<<\n\n");
    CardDistribute(A, B, C, D, cardStack, cardHoldingNum);
        
    PrintAllMemberCard(A, B, C, D, cardHoldingNum);
    
    printf("\n>>>>> Remove Paried Cards <<<<<\n\n");
    RemovePairedCard(A, &cardHoldingNum[0]);
    RemovePairedCard(B, &cardHoldingNum[1]);
    RemovePairedCard(C, &cardHoldingNum[2]);
    RemovePairedCard(D, &cardHoldingNum[3]);

    PrintAllMemberCard(A, B, C, D, cardHoldingNum);

    printf("\n>>>>> Game Start <<<<<\n\n");
    Game(A, B, C, D, cardHoldingNum, playerOrder);
    
    printf("\n>>>>> Game End <<<<<\n\n");
    PrintPlayerOrder(playerOrder);
    
    return 0;
}

void PrintCard(int* p, int length){
       for (int i = 0; i < length; i++){
          printf("\t%d", *(p + i));
       }
}

void PrintAllMemberCard(int* A, int* B, int* C, int* D, int* cardNum){
       printf("A >>");
       PrintCard(A, cardNum[0]);
       printf("\n");
       printf("B >>");
       PrintCard(B, cardNum[1]);
       printf("\n");
       printf("C >>");
       PrintCard(C, cardNum[2]);
       printf("\n");
       printf("D >>");
       PrintCard(D, cardNum[3]);
       printf("\n");
}

void CreateCardStack(int* cardStack){
    for(int i = 0 ; i < 53; ++i){
        if (i == 0) cardStack[i] = 0;
        else cardStack[i] = (i - 1) / 4 + 1;
    }
}

void CardDistribute(int* A, int* B, int* C, int* D, int* cardStack, int* cardNum){
    srand((unsigned int)time(NULL));
    int pick[53];
    for(int i=0; i<53; ++i)
        pick[i] = 0;
    
    // A 넣기
    for(int i = 0; i < cardNum[0]; ++i){
        int randnum = rand() % 53;
        if(!pick[randnum]){
            pick[randnum] = 1; A[i] = cardStack[randnum];
        }
        else{
            i -= 1;
        }
    }
    // B 넣기
    for(int i = 0; i < cardNum[1]; ++i){
        int randnum = rand() % 53;
        if(!pick[randnum]){
            pick[randnum] = 1; B[i] = cardStack[randnum];
        }
        else{
            i -= 1;
        }
    }
    // C 넣기
    for(int i = 0; i < cardNum[2]; ++i){
        int randnum = rand() % 53;
        if(!pick[randnum]){
            pick[randnum] = 1; C[i] = cardStack[randnum];
        }
        else{
            i -= 1;
        }
    }
    // D 넣기
    for(int i = 0; i < cardNum[3]; ++i){
        int randnum = rand() % 53;
        if(!pick[randnum]){
            pick[randnum] = 1; D[i] = cardStack[randnum];
        }
        else{
            i -= 1;
        }
    }
    
}

void ArrayPop(int* arr, int* arrLength, int poplndex){
    
    for(int i= poplndex; i <*arrLength - 1; ++i){
        arr[i] = arr[i+1];
    }
    arr = (int *)malloc(sizeof(int) * (*arrLength - 1));
    
    *arrLength -= 1;
}

void ArrayPush(int* arr, int* arrLength, int num){
    
    *arrLength += 1;
    arr[*arrLength - 1] = num;
    arr = (int *)malloc(sizeof(int) * (*arrLength));
    
}

void RemovePairedCard(int* holdingCard, int* cardNum){
    
    int pick[15][17];
    for(int i=0; i<15; ++i)
        for(int j=0; j<17; ++j)
            pick[i][j] = 0;
        
    for(int i=0; i<*cardNum; ++i){
        int cnt = pick[ holdingCard[i] ][16];
        pick[ holdingCard[i] ][cnt] = i;
        pick[ holdingCard[i] ][16] += 1;
        if (pick[ holdingCard[i] ][16] >= 2){
            int target = holdingCard[i];
            ArrayPop(holdingCard, cardNum, pick[ target ][1]);
            ArrayPop(holdingCard, cardNum, pick[ target ][0]);
            RemovePairedCard(holdingCard, cardNum);
            return ;
        }
    }
    
}

// 각각 사람에 카드 몇개인지 : cardNum = cardHoldingNum
void Game(int* A, int* B, int* C, int* D, int* cardNum, int* playerOrder){
    
    // 첫번째 정하기
    int first = rand() % 4;
    
    // turn : 순서 , winner : 순위 겸 게임 순서에서 제외를 위한 배열, game_done : 4가 되면 종료, player : 각 player 이름 매칭
    int turn[4], winner[4] = {0, 0, 0, 0}, game_done = 0;
    char player_name[4] = {'A', 'B', 'C', 'D'};
    int* player_pointer[4] = { A, B, C, D};
    
    // 첫번째부터 순서정하기 : turn
    for(int i = 0; i <4; ++i){
        int tmp = first + 1;
        if (tmp > 3) tmp = 0;
        turn[first] = tmp;
        first = tmp;
    }
    
    int cur = first, step = 1, score = 1;
    while(game_done != 3){
        // next : cur가 뺏어올 다음 녀석
        
        if (cardNum[cur] == 0)
            for(cur = turn[cur]; winner[cur]; cur = turn[cur]);
        int next = 0;
        for(next = turn[cur]; winner[next]; next = turn[next]);
        
        // 카드 가져오기 + 뻇기기 + 뻇은 거 출력
        int pick = rand() % cardNum[next];
        int picked_num = player_pointer[next][pick];
        
        // 뻇고 뺴앗기기
        ArrayPush(player_pointer[cur], &cardNum[cur], player_pointer[next][pick]);
        RemovePairedCard(player_pointer[cur], &cardNum[cur]);
        ArrayPop(player_pointer[next], &cardNum[next], pick);
        printf("[ Step %d ] \t %c picked %d from %c\n", step++, player_name[cur], picked_num, player_name[turn[cur]]);
        
        // 전체 카드 상황 출력하고
        PrintAllMemberCard(A, B, C, D, cardNum);
        
        // 카드 갯수가 0이 된 친구들은 순위권에서 배제한다.
        if(cardNum[cur] == 0){
            winner[cur] = score;
            ++game_done;
        }
        if(cardNum[next] == 0){
            winner[next] = score;
            ++game_done;
        }
        if(cardNum[next] == 0 || cardNum[cur] == 0 ) score++;
        
        // 뺏어오는 사람의 카드가 없다면
        game_done = 0;
        for(int i=0 ; i<4; ++i){
            if(cardNum[i] == 0) ++game_done;
        }
        
        cur = next;
    }
    
    for(int i=0; i<4; ++i){
        playerOrder[i] = winner[i];
    }
    
}

void PrintPlayerOrder(int* playerOrder){
    int list[4]; char name[4] ={'A', 'B', 'C', 'D'};
    for(int i=0; i<4; ++i){
        if(playerOrder[i] == 0)
            list[3] = i;
        else
            list[playerOrder[i]-1] = i;
    }
    
    for(int i=0; i<4; ++i)
        printf("%dst\t %c Player\n", i+1, name[list[i]]);
}
