#include <iostream>
#include <cmath>
#include <vector>
#include <string>
#define M_PI 3.14159265358979
#define PRGINFOMSG "R/L/C 계산 프로그램\n\n"
#define BRANCHMSG "계산을 진행하시려면 y, 아니면 n을 입력하세요! : "
#define ERRMSG "잘못된 입력입니다. y 또는 n으로 입력해주세요!"
#define EMPTYVEC "아직 입력된 R / L / C 목록이 없습니다!\n\n"
#define USECONTAINERMSG "입력된 값을 이용하시겠습니까? (이용하려면 1, 아니면 0을 입력하세요!) : "
#define USENCONTAINERMSG "몇번 R / L / C 값을 이용하시겠습니까? :"
#define USECONTAINERERRMSG "잘못된 입력입니다. 1과 0중 하나만 입력해주십시오."
using namespace std;

string branch;
int vCnt;
int useContainer;
bool flag = true;

class container {
public:
	double R;
	double L;
	double C;
	int cnt;
	container(double R, double L, double C) : R(R), L(L), C(C) { cnt = ++vCnt; }
	void showData() {
		std::cout << cnt << ") ";
		std::cout << "R : " << R << ", L : " << L << ", C : " << C << endl;
	}
};

vector<container> vContainer;

int main(void) {

	std::cout.precision(10);

	// input a=R, b=L, c=C
	double a, b, c, f, v;

	// output
	double d, e, alpha[3], beta[3];

	std::cout << PRGINFOMSG;

	while (true) {

		// 계속 입력을 위한 수단
		std::cout << BRANCHMSG; cin >> branch; while (getchar() != '\n') {}
		if (branch == "y" || branch == "Y") { std::cout << endl; }
		else if (branch == "n" || branch == "N") { return 0; }
		else { std::cout << ERRMSG; std::cout << endl; continue; }

		// vector에 저장된 R / L / C의 입력 history 출력
		// 입력 X
		if (vContainer.size() == 0) {
			std::cout << EMPTYVEC;
			std::cout << "R : "; std::cin >> a;
			std::cout << "L : "; std::cin >> b;
			std::cout << "C : "; std::cin >> c;
			std::cout << "f : "; std::cin >> f;
			std::cout << "v : "; std::cin >> v;
			vContainer.push_back(container(a, b, c));
			std::cout << endl << endl;
		}
		// 입력 O
		else {
			for (int i = 0; i < vContainer.size(); ++i) {
				vContainer[i].showData();
			}
			while (flag) {
				std::cout << USECONTAINERMSG; cin >> useContainer;
				switch (useContainer) {
				case 1:
					std::cout << USENCONTAINERMSG; cin >> useContainer;
					a = vContainer[useContainer - 1].R;
					b = vContainer[useContainer - 1].L;
					c = vContainer[useContainer - 1].C;
					std::cout << "f : "; std::cin >> f;
					std::cout << "v : "; std::cin >> v;
					std::cout << endl << endl;
					flag = false;
					break;
				case 0:
					// R 입력하라 하고 a로, L 입력하라 하고 b로, C 입력하라 하고 c로 받음
					std::cout << "R : "; std::cin >> a;
					std::cout << "L : "; std::cin >> b;
					std::cout << "C : "; std::cin >> c;
					std::cout << "f : "; std::cin >> f;
					std::cout << "v : "; std::cin >> v;
					vContainer.push_back(container(a, b, c));
					std::cout << endl << endl;
					flag = false;
					break;
				default:
					std::cout<<USECONTAINERERRMSG;
					break;
				}
			}
			flag = true;
		}

		// d와 e 구하는 단계
		std::cout << "phase1> d와 e 구하기" << endl;
		if (c == 0) {
			std::cout << "C = 0" << endl;
			d = a;
			e = (2 * M_PI * b * f);
			std::cout << "d = " << d << endl;
			std::cout << "e = " << e << endl;

		}
		else {
			std::cout << "C !=0" << endl;
			d = a;
			e = (2 * M_PI * b * f) - (1 / (2 * M_PI * c * f));
			std::cout << "d = " << d << endl;
			std::cout << "e = " << e << endl;
		}
		std::cout << endl << endl;

		// alpha beta 구하는 단계
		std::cout << "phase2> alpha와 beta 구하기" << endl << endl;
		alpha[0] = ((a * d * v) / (pow(d, (double)2) + pow(e, (double)2)));
		alpha[1] = ((2 * M_PI * b * f * e * v) / (pow(d, (double)2) + pow(e, (double)2)));
		alpha[2] = ((double)(-1) * v * e) / (2 * M_PI * c * f * (pow(d, (double)2) + pow(e, (double)2)));

		beta[0] = (double)(-1) * ((a * e * v) / (pow(d, (double)2) + pow(e, (double)2)));
		beta[1] = ((2 * M_PI * b * f * d * v) / (pow(d, (double)2) + pow(e, (double)2)));
		beta[2] = ((double)(-1) * v * d) / (2 * M_PI * c * f * (pow(d, (double)2) + pow(e, (double)2)));
		for (int i = 0; i < 3; ++i) {
			if (i == 0) {
				std::cout << "alpha : " << alpha[i] << endl;
				std::cout << "beta : " << beta[i] << endl;
			}
			else if (i == 1) {
				std::cout << "gamma : " << alpha[i] << endl;
				std::cout << "delta : " << beta[i] << endl;
			}
			else {
				std::cout << "Epsilon : " << alpha[i] << endl;
				std::cout << "Y : " << beta[i] << endl;
			}
		}
		std::cout << endl << endl;


		// 출력
		std::cout << "phase3> 출력" << endl << endl;
		for (int i = 0; i < 3; ++i) {
			double atanVal;
			if (alpha[i] > 0) {
				atanVal = atan(beta[i] / alpha[i]) * 180 / M_PI;
			}
			else {
				if(beta[i] >= 0)
					atanVal = atan(beta[i] / alpha[i]) * 180 / M_PI + 180;
				else
					atanVal = atan(beta[i] / alpha[i]) * 180 / M_PI - 180;

			}

			if (i == 0) {
				std::cout << "출력 1" << endl;
				std::cout << "(alpha^2 + beta^2)^(1/2) :\t"<< sqrt((pow(alpha[i], (double)2) + pow(beta[i], (double)2))) << endl;
				std::cout << "arctan(beta/alpha) :\t\t" << atanVal << "(degree)" << endl;
			}
			else if (i == 1) {
				std::cout << "출력 2" << endl;
				std::cout << "(gamma^2 + delta^2)^(1/2) :\t" << sqrt((pow(alpha[i], (double)2) + pow(beta[i], (double)2))) << endl;
				std::cout << "arctan(delta/gamma) :\t\t" << atanVal<< "(degree)" << endl;
			}
			else {
				std::cout << "출력 3" << endl;
				std::cout << "(Epsilon^2 + Y^2)^(1/2) :\t" << sqrt((pow(alpha[i], (double)2) + pow(beta[i], (double)2))) << endl;
				std::cout << "arctan(Y/Epsilon) :\t\t" << atanVal << "(degree)" << endl;
			}
		}
		std::cout << endl << endl;
	}
	
	return 0;
}
