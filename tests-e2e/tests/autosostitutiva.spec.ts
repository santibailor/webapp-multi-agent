import { test, expect } from '@playwright/test';

test.describe('Customer Auto Sostitutiva Flow', () => {

  test('Customer should be able to view requests history', async ({ page }) => {
    await page.goto('http://localhost:3000/customer/requests');
    // Ensure the page loads properly
    await expect(page.locator('h1')).toContainText('Storico Auto Sostitutiva');
    await expect(page.getByRole('link', { name: 'Richiedi Nuova' })).toBeVisible();
    
    // Check table headers
    await expect(page.locator('table th').first()).toContainText('ID');
  });

});

test.describe('Operator Auto Sostitutiva Flow', () => {

  test('Operator should be able to view the dashboard stats', async ({ page }) => {
    await page.goto('http://localhost:3000/operator/dashboard');
    await expect(page.locator('h1')).toContainText('Dashboard Operatore');
    
    // Verify KPI cards are visible
    await expect(page.locator('h2').filter({ hasText: 'Totale auto sostituite' })).toBeVisible();
    await expect(page.locator('h2').filter({ hasText: 'Totale richieste' })).toBeVisible();
    await expect(page.locator('h2').filter({ hasText: 'Spese totali' })).toBeVisible();
    
    // Verify quick action links
    await expect(page.getByRole('link', { name: 'Vedi tutte le Richieste' })).toBeVisible();
    await expect(page.getByRole('link', { name: 'Prenota Auto Sostitutiva' })).toBeVisible();
  });

});
