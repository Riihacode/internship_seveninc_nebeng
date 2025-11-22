import {
  Home,
  Users,
  User,
  PieChart,
  Presentation,
  NotebookText,
  BanknoteArrowDown,
  BadgeCheck,
  UserStar,
  HandCoins,
  Columns3Cog,
  ChartPie,
} from "lucide-react";

export const sideBarMenu = {
  admin: [
    { name: "Beranda", icon: Home, path: "/home" },
    { name: "Verifikasi Data", icon: ChartPie, path: "/verifikasi" },
    { name: "Mitra", icon: User, path: "/mitra" },
    { name: "Customer", icon: Users, path: "/customer" },
    { name: "Pesanan", icon: NotebookText, path: "/pesanan" },
    { name: "Refund", icon: BanknoteArrowDown, path: "/refund" },
    { name: "Laporan", icon: Presentation, path: "/laporan" },
  ],

  superadmin: [
    { name: "Dashboard", icon: PieChart, path: "/sp/dashboard" },
    { name: "Verifikasi Data", icon: ChartPie, path: "/verifikasi" },
    { name: "Layanan", icon: Columns3Cog, path: "/sp/layanan" },
    { name: "Verifikasi Data", icon: BadgeCheck, path: "/sp/verifikasi" },
    { name: "Mitra", icon: User, path: "/mitra" },
    { name: "Customer", icon: Users, path: "/customer" },
    { name: "Admin", icon: UserStar, path: "/admin" },
    { name: "Pesanan", icon: NotebookText, path: "/pesanan" },
    { name: "Refund", icon: BanknoteArrowDown, path: "/refund" },
    { name: "Pencairan Dana", icon: HandCoins, path: "/pencairan" },
    { name: "Laporan", icon: Presentation, path: "/laporan" },
  ],
};
