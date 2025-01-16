package com.wallet.SimpleWalletAPI.controllers;

import com.wallet.SimpleWalletAPI.dtos.WalletDTO;
import com.wallet.SimpleWalletAPI.payload.*;
import com.wallet.SimpleWalletAPI.services.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Operation(summary = "Create a new wallet",
            description = "This endpoint allows user to create a new wallet with a given name.")
    @PostMapping("/create-wallet")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody CreateWalletRequest createWalletRequest) {
        WalletDTO wallet = walletService.createWallet(createWalletRequest.walletName());
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of all user's wallets",
            description = "This endpoint retrieves all wallets for the authenticated user.")
    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        List<WalletDTO> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

    @Operation(summary = "Get wallet details by wallet code",
            description = "This endpoint retrieves details of a wallet by its unique wallet code.")
    @GetMapping("/{walletCode}")
    public ResponseEntity<WalletDTO> getWalletDetails(@PathVariable @Parameter(description = "The wallet code to retrieve wallet details") String walletCode) {
        WalletDTO wallet = walletService.getWalletDetails(walletCode);
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Deposit to the primary wallet",
            description = "This endpoint allows user to deposit a specified amount only to the primary wallet.")
    @PostMapping("/deposit")
    public ResponseEntity<WalletDTO> depositToPrimaryWallet(@RequestBody DepositRequest depositRequest) {
        WalletDTO wallet = walletService.depositToPrimaryWallet(depositRequest.amount(), depositRequest.currency());
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Withdraw from the primary wallet",
            description = "This endpoint allows user to withdraw a specified amount only from the primary wallet.")
    @PostMapping("/withdraw")
    public ResponseEntity<WalletDTO> withdrawFromPrimaryWallet(@RequestBody WithdrawRequest withdrawRequest) {
        WalletDTO wallet = walletService.withdrawFromPrimaryWallet(withdrawRequest.amount(), withdrawRequest.currency());
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Transfer funds to another user wallet",
            description = "This endpoint allows user to transfer funds from it's own primary wallet to another user's primary wallet using their wallet code.")
    @PostMapping("/transfer-to-user")
    public ResponseEntity<WalletDTO> transferToAnotherUser(@RequestBody TransferToUserRequest transferToUserRequest) {
        WalletDTO wallet = walletService.transferMoneyToAnotherUser(transferToUserRequest.toWalletCode(), transferToUserRequest.amount(), transferToUserRequest.currency());
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Transfer funds between own wallets",
            description = "This endpoint allows user to transfer funds between user's own wallets using their respective wallet codes.")
    @PostMapping("/transfer-between-own")
    public ResponseEntity<WalletDTO> transferBetweenOwnWallets(@RequestBody TransferBetweenOwnRequest transferBetweenOwnRequest) {
        WalletDTO wallet = walletService.transferBetweenOwnWallets(transferBetweenOwnRequest.fromWalletCode(), transferBetweenOwnRequest.toWalletCode(), transferBetweenOwnRequest.amount(), transferBetweenOwnRequest.currency());
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Convert the currency of a wallet",
            description = "This endpoint allows user to convert the currency of a specific wallet to the target currency.")
    @PostMapping("/convert-currency")
    public ResponseEntity<WalletDTO> convertWalletCurrency(@RequestBody ConvertCurrencyRequest convertCurrencyRequest) {
        WalletDTO updatedWallet = walletService.convertWalletCurrency(convertCurrencyRequest.walletCode(), convertCurrencyRequest.targetCurrency());
        return ResponseEntity.ok(updatedWallet);
    }

    @Operation(summary = "Delete a wallet by wallet code",
            description = "This endpoint allows user to delete a wallet using its wallet code. Primary wallet cannot be deleted.")
    @DeleteMapping("/{walletCode}")
    public ResponseEntity<String> deleteWallet(@PathVariable @Parameter(description = "The wallet code of the wallet to be deleted") String walletCode) {
        walletService.deleteWallet(walletCode);
        return ResponseEntity.ok("Wallet deleted successfully");
    }
}