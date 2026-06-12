import { CommonModule } from '@angular/common'; import { Component, OnDestroy, OnInit } from '@angular/core'; import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms'; import { Subscription } from 'rxjs'; import { AlertStreamService } from '../../services/alert-stream.service'; import { TransactionService } from '../../services/transaction.service'; import { FraudAlert } from '../../models/fraud-alert.model';
@Component({selector:'app-root', standalone:true, imports:[CommonModule,ReactiveFormsModule], templateUrl:'./dashboard.component.html', styleUrl:'./dashboard.component.css'})
export class DashboardComponent implements OnInit, OnDestroy { alerts:FraudAlert[]=[]; submitMessage=''; streamStatus='CONNECTING'; private sub?:Subscription;
 form=this.fb.nonNullable.group({accountId:['A123',Validators.required], amount:[100,[Validators.required,Validators.min(0.01)]], merchant:['Amazon',Validators.required], location:['US',Validators.required]});
 constructor(private fb:FormBuilder, private tx:TransactionService, private stream:AlertStreamService){}
 ngOnInit(){ this.sub=this.stream.connect().subscribe({next:a=>this.addAlert(a), error:()=>this.streamStatus='DISCONNECTED'}); this.streamStatus='CONNECTED'; }
 submit(){ if(this.form.invalid){ this.form.markAllAsTouched(); return; } this.tx.submit(this.form.getRawValue()).subscribe({next:r=>this.submitMessage=`Accepted ${r.transactionId}`, error:e=>this.submitMessage=e?.error?.message ?? 'Submit failed'}); }
 private addAlert(alert:FraudAlert){ alert.highlighted=alert.riskLevel==='HIGH_RISK'; this.alerts= alert.riskLevel==='HIGH_RISK' ? [alert,...this.alerts] : [...this.alerts, alert]; if(alert.highlighted) setTimeout(()=>alert.highlighted=false,2000); }
 badgeClass(risk:string){ return {'HIGH_RISK':'risk-high','MEDIUM_RISK':'risk-medium','LOW_RISK':'risk-low'}[risk] ?? ''; }
 ngOnDestroy(){ this.sub?.unsubscribe(); }
}
