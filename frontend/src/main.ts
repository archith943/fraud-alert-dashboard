import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { DashboardComponent } from './app/components/dashboard/dashboard.component';
bootstrapApplication(DashboardComponent, {providers:[provideHttpClient(), provideAnimations()]}).catch(err=>console.error(err));
