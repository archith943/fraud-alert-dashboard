export type RiskLevel = 'LOW_RISK' | 'MEDIUM_RISK' | 'HIGH_RISK';
export interface TransactionRequest { accountId:string; amount:number; merchant:string; location:string; }
export interface TransactionAcceptedResponse { transactionId:string; status:string; acceptedAt:string; }
export interface FraudAlert { transactionId:string; accountId:string; amount:number; merchant:string; location:string; riskLevel:RiskLevel; reason:string; evaluatedAt:string; highlighted?:boolean; }
