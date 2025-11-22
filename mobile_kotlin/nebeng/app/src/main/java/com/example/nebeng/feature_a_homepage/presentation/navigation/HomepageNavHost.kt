package com.example.nebeng.feature_a_homepage.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_a_homepage.presentation.HomepageViewModel
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.HomepageCustomerScreenUi
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_01.PassengerRideMotorScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_02.PassengerRideMotorScheduleScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_03.PassengerRideMotorScheduleDetailScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_04.PassengerRideMotorPaymentMethodScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_05.PassengerRideMotorPaymentMethodDetailScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_06.PassengerRideMotorPaymentStatusScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_07.PassengerRideMotorPaymentWaitingScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_08.PassengerRideMotorPaymentSuccessScreen
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_09.PassengerRideMotorOnTheWayScreen

const val NEBENG_MOTOR                      = "nebeng_motor"
const val NEBENG_MOTOR_RIDE_SCHEDULE        = "nebeng_motor_ride_schedule"
const val NEBENG_MOTOR_RIDE_SCHEDULE_DETAIL = "nebeng_motor_ride_schedule_detail"
const val NEBENG_MOTOR_PAYMENT_METHOD       = "nebeng_motor_payment_method"
const val NEBENG_MOTOR_PAYMENT_METHOD_DETAIL= "nebeng_motor_payment_method_detail"
const val NEBENG_MOTOR_PAYMENT_STATUS       = "nebeng_motor_payment_status"
const val NEBENG_MOTOR_PAYMENT_WAITING      = "nebeng_motor_payment_waiting"
const val NEBENG_MOTOR_PAYMENT_SUCCESS      = "nebeng_motor_payment_success"
const val NEBENG_MOTOR_ON_THE_WAY           = "nebeng_motor_on_the_way"

const val ROUTE_PASSENGER_MOTOR_MAP = "passenger_motor_map"
const val ROUTE_PASSENGER_MOBIL = "passenger_mobil"

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageNavHost(
    navController: NavHostController = rememberNavController(),
    userType: String,
    viewModel: HomepageViewModel,
    onRouteChanged: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val startDestination = if (userType.equals("driver", ignoreCase = true)) {
        "homepage_driver"
    } else {
        "homepage_customer"
    }

    // ⬇️ Listener untuk mendeteksi pergantian composable route
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { route ->
                onRouteChanged(route)   // ⬅️ kirim ke Fragment
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("homepage_customer") {
            HomepageCustomerScreenUi(
                state = uiState,
                onMenuMotorClick = {
                    navController.navigate(NEBENG_MOTOR)
                },
                onMenuOpenMapClick = {
                    navController.navigate(ROUTE_PASSENGER_MOTOR_MAP)  // 🔥 open map screen
                }
            )
        }

        // PAGE 01
        composable(NEBENG_MOTOR) {
            PassengerRideMotorScreen(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(NEBENG_MOTOR_RIDE_SCHEDULE) }
            )
        }

        // PAGE 02
        composable(NEBENG_MOTOR_RIDE_SCHEDULE) {
            PassengerRideMotorScheduleScreen(
                onBack = { navController.popBackStack() },
                onDetailClick = { navController.navigate(NEBENG_MOTOR_RIDE_SCHEDULE_DETAIL) }
            )
        }

        // PAGE 03
        composable(NEBENG_MOTOR_RIDE_SCHEDULE_DETAIL) {
            PassengerRideMotorScheduleDetailScreen(
                onBack = { navController.popBackStack() },
                onPay = { navController.navigate(NEBENG_MOTOR_PAYMENT_METHOD) }
            )
        }

        // PAGE 04
        composable(NEBENG_MOTOR_PAYMENT_METHOD) {
            PassengerRideMotorPaymentMethodScreen (
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(NEBENG_MOTOR_PAYMENT_METHOD_DETAIL) }
            )
        }

        // PAGE 05
        composable(NEBENG_MOTOR_PAYMENT_METHOD_DETAIL) {
            PassengerRideMotorPaymentMethodDetailScreen(
                onBack = { navController.popBackStack() },
                onPay = { navController.navigate(NEBENG_MOTOR_PAYMENT_STATUS) }
            )
        }

        // PAGE 06
        composable(NEBENG_MOTOR_PAYMENT_STATUS) {
            PassengerRideMotorPaymentStatusScreen(
                onBack = { navController.popBackStack() },
                onCheckStatus = { navController.navigate(NEBENG_MOTOR_PAYMENT_WAITING) }
            )
        }

        // PAGE 07
        composable(NEBENG_MOTOR_PAYMENT_WAITING) {
            PassengerRideMotorPaymentWaitingScreen(
                onBack = { navController.popBackStack() },
                onCheckStatus = { navController.navigate(NEBENG_MOTOR_PAYMENT_SUCCESS) }
            )
        }

        // PAGE 08
        composable(NEBENG_MOTOR_PAYMENT_SUCCESS) {
            PassengerRideMotorPaymentSuccessScreen(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(NEBENG_MOTOR_ON_THE_WAY) }
            )
        }

        // PAGE 09
        composable(NEBENG_MOTOR_ON_THE_WAY) {
            PassengerRideMotorOnTheWayScreen(
                onBack = { navController.popBackStack() },
                onCancelOrder = { navController.navigate(NEBENG_MOTOR_PAYMENT_METHOD) }
            )
        }

        // PAGE 10


        // 🔥 ROUTE MAP WAJIB ADA
        composable(ROUTE_PASSENGER_MOTOR_MAP) {
            PassengerRideMotorOnTheWayScreen()
        }

        composable(ROUTE_PASSENGER_MOBIL) {
            PassengerRideMotorScreen(
                onBack = { navController.popBackStack() },
                onNext = { /* nanti lanjut ke page_02 */ }
            )
        }

        composable("homepage_driver") {
//            HomepageDriverScreenUi(
//                state = uiState,
//                onVerifyClicked = {
//                    // navigasi ke halaman verifikasi dokumen
//                    navController.navigate("verify_documents")
//                }
//            )
        }

        // Placeholder halaman verifikasi dokumen (nanti diganti actual screen)
        composable("verify_documents") {
            Text("Halaman Verifikasi Dokumen (coming soon)")
        }
    }
}